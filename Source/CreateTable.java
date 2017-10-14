class CreateTable
{    
    private static String name;
    private static String block;        
    
    CreateTable(String name, String block)
    {
        this.name = name;
        this.block = block;
    }
    
    public void parse()
    {
        block = new StringBuilder(name).deleteCharAt(name.indexOf('{')).deleteCharAt(name.indexOf('}')).toString(); //removes the {} from the block
        block.split(",");
    }
    
    public void createTable()
    {
        
        
    }            
}
