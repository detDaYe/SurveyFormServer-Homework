package org.lumenk.object.webserver.util;

public class GeneralJsonMaker {

    private MyQueue<Pair> pairs = new MyQueue<>();

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");
        int count = pairs.size();
        Pair temp;

        for(int i = 0; i < count; i++){
            temp = pairs.dequeue();
            stringBuilder.append(temp.attribute);
            stringBuilder.append(":");
            stringBuilder.append(temp.value);
            if(i < count - 1) stringBuilder.append(",");
        }

        return stringBuilder.toString();
    }

    protected void addIntAttribute(String name, int value){
        addAttribute(name, value + "");
    }

    protected void addBooleanAttribute(String name, boolean value){
        addAttribute(name, value ? "true" : "false");
    }

    protected void addStringAttribute(String name, String value){
        addAttribute(name, "\"" + value + "\"");
    }

    private void addAttribute(String name, String value){
        pairs.enqueue(new Pair("\"" + name + "\"", value));
    }

    private static class Pair{

        public Pair(String attribute, String value) {
            this.attribute = attribute;
            this.value = value;
        }

        private final String attribute;
        private final String value;
    }
}
