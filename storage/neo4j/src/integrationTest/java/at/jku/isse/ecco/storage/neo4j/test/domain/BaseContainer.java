package at.jku.isse.ecco.storage.neo4j.test.domain;

import org.neo4j.ogm.annotation.Relationship;

public class BaseContainer extends NeoEntity {



    int integerValue;
    String stringValue;

    @Relationship(type = "containingRepo", direction = Relationship.INCOMING)
    TreeRootNode tree;

    SpecializedClass1 referenceToObject;

    public BaseContainer() {}

    public BaseContainer(int integerValue, String stringValue, TreeRootNode tree, SpecializedClass1 referenceToObject) {
        this.integerValue = integerValue;
        this.stringValue = stringValue;
        this.tree = tree;
        this.referenceToObject = referenceToObject;
    }

    public int getIntegerValue() {
        return integerValue;
    }

    public void setIntegerValue(int integerValue) {
        this.integerValue = integerValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public TreeRootNode getTree() {
        return tree;
    }

    public void setTree(TreeRootNode tree) {
        this.tree = tree;
        tree.setContainingRepo(this);
    }

    public SpecializedClass1 getReferenceToObject() {
        return referenceToObject;
    }

    public void setReferenceToObject(SpecializedClass1 referenceToObject) {
        this.referenceToObject = referenceToObject;
    }
}
