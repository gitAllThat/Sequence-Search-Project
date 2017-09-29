package edu.gcccd.csis.p1;

public class SequenceSearchImpl extends SequenceSearch {

    public SequenceSearchImpl(final String content, final String start, final String end) {
        super(content, start, end);
    }

    @Override
    public String[] getAllTaggedSequences() {
        // Create an array to return
        String[] returnString;
        // Create index's to hold data while searching string.
        int indexOne = content.indexOf(startTag);
        int indexTwo = content.indexOf(endTag, indexOne+startTag.length());
        // Var for holding the current index count
        int indexCount = 0;
        // Int of the total amount of sequences for this content
        final int MAX = getSeqAmount();

        // Set array to amount of index's found
        returnString = new String[MAX];

        // There is no tags for this string. Return it empty.
        if(MAX == 0)
        {
            return returnString;
        }

        // Loop through the content
        while (indexOne >= 0 && indexTwo >= 0){
            returnString[indexCount] = content.substring(indexOne + startTag.length(), indexTwo);
            indexOne = content.indexOf(startTag, indexTwo+endTag.length());
            indexTwo = content.indexOf(endTag, indexOne+startTag.length());
            indexCount++;
        }
        // Return the string
        return returnString;
    }

    @Override
    public String getLongestTaggedSequence() {

        // Set searchThis to be all the tagged sequences
        String[] searchThis = getAllTaggedSequences();

        // There are no tags for this string, return NULL
        if(searchThis.length == 0)
        {
            return null;
        }
        // Setup a temp string, longest to hold the longest sequence
        String longest = "";
        // Loop through searchThis array of sequences.
        for (int i = 0; i < getSeqAmount(); i++){
            //System.out.println(searchThis[i]);
            if (longest.length() <= searchThis[i].length()){
                longest = searchThis[i];
            }
        }

        return longest;
    }

    @Override
    public String displayStringArray() {
        // Temp string
        String rString = "";

        String[] getThis = getAllTaggedSequences();
        // Loop through sequences, make string out of sequences with their length
        for (int i = 0; i < getThis.length; i++){
            rString = rString + getThis[i] + " : " + getThis[i].length() +"\n";
        }

       return rString;
    }

    @Override
    public String toString() {
        String returnString;
        // Replace all start tags with ""
        returnString = content.replace(startTag, "");
        // Replace all end tags with ""
        returnString = returnString.replace(endTag, "");

        //System.out.println("RETURN STRING: " + returnString);

        return returnString;
    }

    // Method for getting the total amount of sequences
    private int getSeqAmount(){
        int indexOne = content.indexOf(startTag);
        int indexTwo = content.indexOf(endTag);
        int indexCount = 0;
        // Loop through content once to find out how long array needs to be
        while (indexOne >= 0 && indexTwo >= 0) {
            //System.out.println("INDEXONE PRE: " + indexOne);
            indexOne = content.indexOf(startTag, indexTwo + endTag.length());
            //System.out.println("INDEXONE POST: " + indexOne + " INDEXTWO PRE: " + indexTwo);
            indexTwo = content.indexOf(endTag, indexOne + startTag.length());
            //System.out.println("INDEXTWO POST: " + indexTwo);
            indexCount++;
        }
        //System.out.println("TOTAL TAGGED AMOUNT: " + indexCount);
        return indexCount;
    }
}