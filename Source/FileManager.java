class FileManager
{
    private static String root = "/home/parshv/Desktop/Folder/";
    private static String file_name;
    private static File target_meta;
    private static File target;
    private static XMLOutputFactory factory;
    private static StringWriter sw;    
    private static XMLStreamWriter xmlsw;    
    private static String xmlString;
    
    public static final String DATABASE_TAG = "database";
    public static final String COLNUM_TAG = "colnum";
    public static final String ROWNUM_TAG = "rownum";
    public static final String COL_TAG = "col";
    
    public static final String TYPE_ATTRIBUTE = "type";
    public static final String DEFAULT_ATTRIBUTE = "default";
    public static final String NO_ATTRIBUTE = "no";
    
    
    static {
        factory = XMLOutputFactory.newInstance();
        sw = new StringWriter();
        
        try {
            xmlsw = factory.createXMLStreamWriter(sw);
        } 
        catch (XMLStreamException ex) {
            System.out.println("StAX parser error");
            System.exit(1);
        }
    }
    
    public static void setRoot(String root) {
        FileManager.root = root;
    }            
    
    private static void initTarget(String f_name)
    {
        file_name = f_name;               
        
        File f1 = new File(root+file_name+"_meta.xml");
        File f2 = new File(root+file_name+".xml");
        
        target_meta = f1;
        target = f2;
    }
    
    static void useFile(String f_name)
    {
        initTarget(f_name); 
        if(!(target.exists() && target_meta.exists())) throw new TableNotCreatedException();                        
    }
    
    static void createFile(String f_name) throws IOException
    {
        try
        {
            useFile(f_name);
            throw new TableAlreadyExistsException();
        }
        
        catch(TableNotCreatedException ex)
        {
            target.createNewFile();
            target_meta.createNewFile();
        }
    }
    
    
    static void clear()
    {        
        file_name = null;
        target = null;
    }

    static void startDocument() throws XMLStreamException
    {       
        xmlsw.writeStartDocument();        
    }

    static void endDocument() throws XMLStreamException, FileNotFoundException {
        xmlsw.writeEndDocument();
        xmlString = sw.getBuffer().toString();
        
        PrintWriter pw = new PrintWriter(
                                new BufferedWriter(
                                        new OutputStreamWriter(
                                               new FileOutputStream(target_meta.getAbsolutePath())
                                        )
                                )
                            );
                
        pw.println(xmlString);        
        pw.close();
        System.out.println(xmlString);
        System.out.println(target_meta.getAbsolutePath());
    }    
    
    static void insertStartTag(String tag_name, NameValuePair[] list, String content) throws XMLStreamException
    {
        xmlsw.writeStartElement(tag_name);
       
        if(list != null)
        {
            for(int index = 0; index < list.length; index++)        
                xmlsw.writeAttribute(list[index].name, list[index].value);
        }
        
        xmlsw.writeCharacters(content);        
    }
    
    static void insertEndTag() throws XMLStreamException
    {
        xmlsw.writeEndElement();
    }
}
