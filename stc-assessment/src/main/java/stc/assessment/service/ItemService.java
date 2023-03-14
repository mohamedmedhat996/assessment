package stc.assessment.service;

import stc.assessment.entity.Group;
import stc.assessment.entity.Item;

public interface ItemService {
    Item findItemByName(String name);
    Item saveItem(String itemName, Group itemGroup,String itemType , Item parentItem);

    boolean checkItemHasGroupID(String itemName, Group itemGroup);
}
