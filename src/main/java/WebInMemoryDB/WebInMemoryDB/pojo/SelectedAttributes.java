package WebInMemoryDB.WebInMemoryDB.pojo;

import DB.Attributes.StudentAttributeType;

public class SelectedAttributes {
    private StudentAttributeType[] selectedAttributes;
    private String conditionAttribute;
    private String operator;
    private String attributeValue;

    public void setSelectedAttributes(StudentAttributeType[] selectedAttributes) {
        this.selectedAttributes = selectedAttributes;
    }

    public StudentAttributeType[] getSelectedAttributes() {
        return selectedAttributes;
    }

    public String getConditionAttribute() {
        return conditionAttribute;
    }

    public void setConditionAttribute(String conditionAttribute) {
        this.conditionAttribute = conditionAttribute;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
}
