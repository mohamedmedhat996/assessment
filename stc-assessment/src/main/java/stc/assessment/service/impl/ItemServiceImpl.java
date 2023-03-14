package stc.assessment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import stc.assessment.entity.Group;
import stc.assessment.entity.Item;
import stc.assessment.entity.User;
import stc.assessment.repository.ItemsRepository;
import stc.assessment.repository.UsersRepository;
import stc.assessment.service.ItemService;
import stc.assessment.service.UserService;
import stc.assessment.util.Constants;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemsRepository itemsRepository;


    @Override
    public Item findItemByName(String name) {
        return itemsRepository.findByName(name);
    }

    @Override
    public Item saveItem(String itemName, Group itemGroup,String itemType, Item parentItem) {
            return itemsRepository.save(new Item(901L, itemType, itemName, itemGroup, parentItem, null));
    }

    @Override
    public boolean checkItemHasGroupID(String itemName, Group itemGroup) {
        if(ObjectUtils.isEmpty(itemsRepository.findByNameAndPermissionGroupId(itemName,itemGroup))){
            return false;
        }
        return true;
    }
}
