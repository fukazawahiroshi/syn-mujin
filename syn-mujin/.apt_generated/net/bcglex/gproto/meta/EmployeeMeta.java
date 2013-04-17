package net.bcglex.gproto.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-04-16 11:38:30")
/** */
public final class EmployeeMeta extends org.slim3.datastore.ModelMeta<net.bcglex.gproto.model.Employee> {

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee> birthday = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee>(this, "birthday", "birthday");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee> department = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee>(this, "department", "department");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee> email = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee>(this, "email", "email");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee> fname = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee>(this, "fname", "fname");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee> image = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee>(this, "image", "image");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee> kanaFname = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee>(this, "kanaFname", "kanaFname");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee> kanaName = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee>(this, "kanaName", "kanaName");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee> kanaSname = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee>(this, "kanaSname", "kanaSname");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.Employee, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.Employee, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee> location = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee>(this, "location", "location");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.Employee, java.lang.Boolean> needToShow = new org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.Employee, java.lang.Boolean>(this, "needToShow", "needToShow", java.lang.Boolean.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee> phoneNumber1 = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee>(this, "phoneNumber1", "phoneNumber1");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee> phoneNumber2 = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee>(this, "phoneNumber2", "phoneNumber2");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee> phoneNumber3 = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee>(this, "phoneNumber3", "phoneNumber3");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee> section = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee>(this, "section", "section");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee> sname = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee>(this, "sname", "sname");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee> subsection = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Employee>(this, "subsection", "subsection");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.Employee, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.Employee, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final EmployeeMeta slim3_singleton = new EmployeeMeta();

    /**
     * @return the singleton
     */
    public static EmployeeMeta get() {
       return slim3_singleton;
    }

    /** */
    public EmployeeMeta() {
        super("Employee", net.bcglex.gproto.model.Employee.class);
    }

    @Override
    public net.bcglex.gproto.model.Employee entityToModel(com.google.appengine.api.datastore.Entity entity) {
        net.bcglex.gproto.model.Employee model = new net.bcglex.gproto.model.Employee();
        model.setBirthday((java.lang.String) entity.getProperty("birthday"));
        model.setDepartment((java.lang.String) entity.getProperty("department"));
        model.setEmail((java.lang.String) entity.getProperty("email"));
        model.setFname((java.lang.String) entity.getProperty("fname"));
        model.setImage((java.lang.String) entity.getProperty("image"));
        model.setKanaFname((java.lang.String) entity.getProperty("kanaFname"));
        model.setKanaName((java.lang.String) entity.getProperty("kanaName"));
        model.setKanaSname((java.lang.String) entity.getProperty("kanaSname"));
        model.setKey(entity.getKey());
        model.setLocation((java.lang.String) entity.getProperty("location"));
        model.setNeedToShow((java.lang.Boolean) entity.getProperty("needToShow"));
        model.setPhoneNumber1((java.lang.String) entity.getProperty("phoneNumber1"));
        model.setPhoneNumber2((java.lang.String) entity.getProperty("phoneNumber2"));
        model.setPhoneNumber3((java.lang.String) entity.getProperty("phoneNumber3"));
        model.setSection((java.lang.String) entity.getProperty("section"));
        model.setSname((java.lang.String) entity.getProperty("sname"));
        model.setSubsection((java.lang.String) entity.getProperty("subsection"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        net.bcglex.gproto.model.Employee m = (net.bcglex.gproto.model.Employee) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("birthday", m.getBirthday());
        entity.setProperty("department", m.getDepartment());
        entity.setProperty("email", m.getEmail());
        entity.setProperty("fname", m.getFname());
        entity.setProperty("image", m.getImage());
        entity.setProperty("kanaFname", m.getKanaFname());
        entity.setProperty("kanaName", m.getKanaName());
        entity.setProperty("kanaSname", m.getKanaSname());
        entity.setProperty("location", m.getLocation());
        entity.setProperty("needToShow", m.getNeedToShow());
        entity.setProperty("phoneNumber1", m.getPhoneNumber1());
        entity.setProperty("phoneNumber2", m.getPhoneNumber2());
        entity.setProperty("phoneNumber3", m.getPhoneNumber3());
        entity.setProperty("section", m.getSection());
        entity.setProperty("sname", m.getSname());
        entity.setProperty("subsection", m.getSubsection());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        net.bcglex.gproto.model.Employee m = (net.bcglex.gproto.model.Employee) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        net.bcglex.gproto.model.Employee m = (net.bcglex.gproto.model.Employee) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        net.bcglex.gproto.model.Employee m = (net.bcglex.gproto.model.Employee) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        net.bcglex.gproto.model.Employee m = (net.bcglex.gproto.model.Employee) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        net.bcglex.gproto.model.Employee m = (net.bcglex.gproto.model.Employee) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getBirthday() != null){
            writer.setNextPropertyName("birthday");
            encoder0.encode(writer, m.getBirthday());
        }
        if(m.getDepartment() != null){
            writer.setNextPropertyName("department");
            encoder0.encode(writer, m.getDepartment());
        }
        if(m.getEmail() != null){
            writer.setNextPropertyName("email");
            encoder0.encode(writer, m.getEmail());
        }
        if(m.getFname() != null){
            writer.setNextPropertyName("fname");
            encoder0.encode(writer, m.getFname());
        }
        if(m.getImage() != null){
            writer.setNextPropertyName("image");
            encoder0.encode(writer, m.getImage());
        }
        if(m.getKanaFname() != null){
            writer.setNextPropertyName("kanaFname");
            encoder0.encode(writer, m.getKanaFname());
        }
        if(m.getKanaName() != null){
            writer.setNextPropertyName("kanaName");
            encoder0.encode(writer, m.getKanaName());
        }
        if(m.getKanaSname() != null){
            writer.setNextPropertyName("kanaSname");
            encoder0.encode(writer, m.getKanaSname());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getLocation() != null){
            writer.setNextPropertyName("location");
            encoder0.encode(writer, m.getLocation());
        }
        if(m.getNeedToShow() != null){
            writer.setNextPropertyName("needToShow");
            encoder0.encode(writer, m.getNeedToShow());
        }
        if(m.getPhoneNumber1() != null){
            writer.setNextPropertyName("phoneNumber1");
            encoder0.encode(writer, m.getPhoneNumber1());
        }
        if(m.getPhoneNumber2() != null){
            writer.setNextPropertyName("phoneNumber2");
            encoder0.encode(writer, m.getPhoneNumber2());
        }
        if(m.getPhoneNumber3() != null){
            writer.setNextPropertyName("phoneNumber3");
            encoder0.encode(writer, m.getPhoneNumber3());
        }
        if(m.getSection() != null){
            writer.setNextPropertyName("section");
            encoder0.encode(writer, m.getSection());
        }
        if(m.getSname() != null){
            writer.setNextPropertyName("sname");
            encoder0.encode(writer, m.getSname());
        }
        if(m.getSubsection() != null){
            writer.setNextPropertyName("subsection");
            encoder0.encode(writer, m.getSubsection());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected net.bcglex.gproto.model.Employee jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        net.bcglex.gproto.model.Employee m = new net.bcglex.gproto.model.Employee();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("birthday");
        m.setBirthday(decoder0.decode(reader, m.getBirthday()));
        reader = rootReader.newObjectReader("department");
        m.setDepartment(decoder0.decode(reader, m.getDepartment()));
        reader = rootReader.newObjectReader("email");
        m.setEmail(decoder0.decode(reader, m.getEmail()));
        reader = rootReader.newObjectReader("fname");
        m.setFname(decoder0.decode(reader, m.getFname()));
        reader = rootReader.newObjectReader("image");
        m.setImage(decoder0.decode(reader, m.getImage()));
        reader = rootReader.newObjectReader("kanaFname");
        m.setKanaFname(decoder0.decode(reader, m.getKanaFname()));
        reader = rootReader.newObjectReader("kanaName");
        m.setKanaName(decoder0.decode(reader, m.getKanaName()));
        reader = rootReader.newObjectReader("kanaSname");
        m.setKanaSname(decoder0.decode(reader, m.getKanaSname()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("location");
        m.setLocation(decoder0.decode(reader, m.getLocation()));
        reader = rootReader.newObjectReader("needToShow");
        m.setNeedToShow(decoder0.decode(reader, m.getNeedToShow()));
        reader = rootReader.newObjectReader("phoneNumber1");
        m.setPhoneNumber1(decoder0.decode(reader, m.getPhoneNumber1()));
        reader = rootReader.newObjectReader("phoneNumber2");
        m.setPhoneNumber2(decoder0.decode(reader, m.getPhoneNumber2()));
        reader = rootReader.newObjectReader("phoneNumber3");
        m.setPhoneNumber3(decoder0.decode(reader, m.getPhoneNumber3()));
        reader = rootReader.newObjectReader("section");
        m.setSection(decoder0.decode(reader, m.getSection()));
        reader = rootReader.newObjectReader("sname");
        m.setSname(decoder0.decode(reader, m.getSname()));
        reader = rootReader.newObjectReader("subsection");
        m.setSubsection(decoder0.decode(reader, m.getSubsection()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}