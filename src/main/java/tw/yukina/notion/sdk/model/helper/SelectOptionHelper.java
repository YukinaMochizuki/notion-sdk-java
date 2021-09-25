package tw.yukina.notion.sdk.model.helper;

import tw.yukina.notion.sdk.model.OptionColor;
import tw.yukina.notion.sdk.model.common.SelectOption;

import java.util.ArrayList;
import java.util.List;

public final class SelectOptionHelper {

    public static List<SelectOption> getSelectOptions(String[] options){
        List<SelectOption> selectOptions = new ArrayList<>();

        for(String str: options){
            SelectOption selectOption = new SelectOption();
            selectOption.setName(str);
            selectOption.setColor(OptionColor.randomColor());
            selectOptions.add(selectOption);
        }

        return selectOptions;
    }

}
