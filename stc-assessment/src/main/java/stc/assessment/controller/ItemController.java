package stc.assessment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import stc.assessment.entity.Group;
import stc.assessment.entity.Item;
import stc.assessment.entity.Permission;
import stc.assessment.entity.User;
import stc.assessment.message.ResponseMessage;
import stc.assessment.repository.*;
import stc.assessment.service.*;
import stc.assessment.util.Constants;
import stc.assessment.view.ItemDTO;

import java.util.List;


@RestController
@RequestMapping("/space")
@RequiredArgsConstructor
public class ItemController {

    private final GroupService groupService;
    private final UserService userService;
    private final PermissionService permissionService;
    private final ItemService itemService;


    @PostMapping("/")
    public ResponseEntity<ResponseMessage> createSpace(@RequestParam("name") String name) {
        String message = "";
        try {
            Group group = groupService.getGroupByName(Constants.GROUP_ADMIN);
            if (!ObjectUtils.isEmpty(group)) {
                Item space = itemService.saveItem(name, group, Constants.TYPE_FOLDER, null);
                if (!ObjectUtils.isEmpty(space)) {
                    message = "Create Successfully";
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
                } else {
                    throw new Exception("Failed to save the folder in database");
                }
            } else {
                message = "No Group Admin Found";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        } catch (Exception e) {
            message = e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @PostMapping("/folder")
    public ResponseEntity<ResponseMessage> createFolder(@RequestParam("name") String name,
                    @RequestParam("userEmail") String userEmail, @RequestParam("parentName") String parentName) {
        String message = "";
        try {
            User currentUser = userService.getUserByEmail(userEmail);
            if(!ObjectUtils.isEmpty(currentUser)) {
                Permission userPermission = permissionService.checkUserPermission(currentUser, Constants.PERMISSION_EDIT);
                if (!ObjectUtils.isEmpty(userPermission)) {
                    // Save in database
                    Item parentItem = itemService.findItemByName(parentName);
                    Item item = itemService.saveItem(name,userPermission.getGroupId(),Constants.TYPE_FOLDER,parentItem);
                    if (!ObjectUtils.isEmpty(item)) {
                        message = "Create Successfully";
                        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
                    } else {
                        throw new Exception("Failed to save the folder in database");
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
            message = e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

}
