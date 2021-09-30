package tw.yukina.notion.sdk.model.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import tw.yukina.notion.sdk.model.OptionColor;
import tw.yukina.notion.sdk.model.common.SelectOption;

import java.util.ArrayList;
import java.util.List;

public final class JsonNodeHelper {

    public static String getObjectId(JsonNode jsonNode){
        return jsonNode.get("id").asText();
    }

    public static String getDatabaseParentId(JsonNode jsonNode){
        return jsonNode.get("parent").get("database_id").asText();
    }

    public static String getPageParentId(JsonNode jsonNode){
        return jsonNode.get("parent").get("page_id").asText();
    }

    public static String getPropertyId(JsonNode jsonNode, String propertyName) {
        return jsonNode.get("properties").get(propertyName).get("id").asText();
    }

    public static String getDatabaseRelationDatabaseId(JsonNode jsonNode, String propertyName){
        return jsonNode.get("properties").get(propertyName).get("relation").get("database_id").asText();
    }

    public static String getDatabaseRelationSyncedPropertyName(JsonNode jsonNode, String propertyName){
        return jsonNode.get("properties").get(propertyName).get("relation").get("synced_property_name").asText();
    }

    public static String getDatabaseRelationSyncedPropertyId(JsonNode jsonNode, String propertyName){
        return jsonNode.get("properties").get(propertyName).get("relation").get("synced_property_id").asText();
    }

    public static List<SelectOption> getMultiSelectPropertyOptions(JsonNode jsonNode, String propertyName) {

        List<SelectOption> selectOptions = new ArrayList<>();
        ArrayNode arrayNode = (ArrayNode) jsonNode.get("properties").get(propertyName).get("multi_select").get("options");

        for(JsonNode node : arrayNode) selectOptions.add(packageSelectOption(node));


        return selectOptions;
    }

    public static SelectOption getSelectPropertyOption(JsonNode jsonNode, String propertyName) {
        return packageSelectOption(jsonNode.get("properties").get(propertyName).get("select"));
    }

    public static List<SelectOption> getSelectPropertyOptions(JsonNode jsonNode, String propertyName) {

        List<SelectOption> selectOptions = new ArrayList<>();
        ArrayNode arrayNode = (ArrayNode) jsonNode.get("properties").get(propertyName).get("select").get("options");

        for(JsonNode node : arrayNode) selectOptions.add(packageSelectOption(node));


        return selectOptions;
    }

    private static SelectOption packageSelectOption(JsonNode jsonNode){
        SelectOption selectOption = new SelectOption();
        selectOption.setId(jsonNode.get("id").asText());
        selectOption.setName(jsonNode.get("name").asText());
        selectOption.setColor(OptionColor.getEnum(jsonNode.get("color").asText()));

        return selectOption;
    }
}
