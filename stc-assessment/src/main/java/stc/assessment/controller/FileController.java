package stc.assessment.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.QueryParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stc.assessment.entity.*;
import stc.assessment.message.ResponseMessage;
import stc.assessment.service.*;
import stc.assessment.util.Constants;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    private final UserService userService;
    private final PermissionService permissionService;
    private final ItemService itemService;
    private final GroupService groupService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(
            @RequestParam("file") MultipartFile file, @RequestParam("name") String name,
            @RequestParam("userEmail") String userEmail, @RequestParam("parentName") String parentName) {
        String message = "";
        try {
            User currentUser = userService.getUserByEmail(userEmail);
            if(!ObjectUtils.isEmpty(currentUser)) {
                Permission userPermission = permissionService.checkUserPermission(currentUser, Constants.PERMISSION_EDIT);
                if (!ObjectUtils.isEmpty(userPermission)) {
                    // Save in database
                    Item parentItem = itemService.findItemByName(parentName);
                    Item fileItem = itemService.saveItem(name,userPermission.getGroupId(),Constants.TYPE_FILE,parentItem);
                    if (!ObjectUtils.isEmpty(fileItem)) {

                        fileService.save(file, fileItem);
                        message = "Uploaded the file successfully: " + file.getOriginalFilename();
                        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
                    } else {
                        throw new Exception("Failed to save the file in database");
                    }
                } else {
                    message = "User does not has " + Constants.PERMISSION_EDIT + " access to this space";
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseMessage(message));
                }
            } else {
                message = "User not found";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message));
            }
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/download/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> downlaod(@PathVariable String filename,
            @RequestParam("userEmail") String userEmail) {
        String message = "";
        try {
            User currentUser = userService.getUserByEmail(userEmail);
            if(!ObjectUtils.isEmpty(currentUser)) {
                List<Group> groups = groupService.getGroupByUser(currentUser);
                if (!ObjectUtils.isEmpty(groups)) {
                    for (Group group:groups){
                        if(itemService.checkItemHasGroupID(filename,group)){
                            Resource file = fileService.download(filename);
                            return ResponseEntity.ok()
                                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                                    .body(file);
                        }
                    }
                    message = "This user not able to download this file";
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message));
                } else {
                    message = "No group found to this user";
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message));
                }
            } else {
                message = "User not found";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message));
            }
        } catch (Exception e) {
            message = "Could not downlaod the file: " + filename + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

}
