class CreateTable
{    
    private static String name;
    private static String block;        
    
    CreateTable(String name, String block)
    {
        this.name = name;
        this.block = block;
    }
    
    public void parse() throws Exception
    {
        block = new StringBuilder(block).deleteCharAt(0).deleteCharAt(block.length() - 2).toString(); //removes the {} from the block
        String[] attrib_list = block.split(",");
                
        FileManager.createFile(name);
        
        FileManager.startDocument();
        FileManager.insertStartTag("root", null, null);
        
        FileManager.insertStartTag(FileManager.DATABASE_TAG, null, "test");
        FileManager.insertEndTag();
        
        FileManager.insertStartTag(FileManager.COLNUM_TAG, null, Integer.toString(attrib_list.length));
        FileManager.insertEndTag();                
        
        FileManager.insertStartTag(FileManager.ROWNUM_TAG, null, "0");
        FileManager.insertEndTag();                
        
        for(int ctr = 0; ctr < attrib_list.length; ctr++)
        {
            String[] tokens = attrib_list[ctr].trim().split(" ");
            
            // check for primary-foreign statement
            // check for normal statement
            
            NameValuePair[] list = new NameValuePair[3];
            
            list[0] = new NameValuePair(FileManager.NO_ATTRIBUTE, Integer.toString(ctr + 1));
            list[1] = new NameValuePair(FileManager.TYPE_ATTRIBUTE, tokens[1]);
            list[2] = new NameValuePair(FileManager.DEFAULT_ATTRIBUTE, tokens[3]);
            
            FileManager.insertStartTag("col", list, tokens[0]);
            FileManager.insertEndTag();
        }
        
        FileManager.insertEndTag();
        FileManager.endDocument();
    }
    
    public void createTable()
    {
        
        
        
    }            
}
