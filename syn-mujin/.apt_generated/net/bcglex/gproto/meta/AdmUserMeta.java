package net.bcglex.gproto.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-04-16 20:48:35")
/** */
public final class AdmUserMeta extends org.slim3.datastore.ModelMeta<net.bcglex.gproto.model.AdmUser> {

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.AdmUser> domain = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.AdmUser>(this, "domain", "domain");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.AdmUser> email = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.AdmUser>(this, "email", "email");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.AdmUser, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.AdmUser, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.AdmUser> location = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.AdmUser>(this, "location", "location");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.AdmUser> name = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.AdmUser>(this, "name", "name");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.AdmUser> organization = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.AdmUser>(this, "organization", "organization");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.AdmUser, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.AdmUser, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final AdmUserMeta slim3_singleton = new AdmUserMeta();

    /**
     * @return the singleton
     */
    public static AdmUserMeta get() {
       return slim3_singleton;
    }

    /** */
    public AdmUserMeta() {
        super("AdmUser", net.bcglex.gproto.model.AdmUser.class);
    }

    @Override
    public net.bcglex.gproto.model.AdmUser entityToModel(com.google.appengine.api.datastore.Entity entity) {
        net.bcglex.gproto.model.AdmUser model = new net.bcglex.gproto.model.AdmUser();
        model.setDomain((java.lang.String) entity.getProperty("domain"));
        model.setEmail((java.lang.String) entity.getProperty("email"));
        model.setKey(entity.getKey());
        model.setLocation((java.lang.String) entity.getProperty("location"));
        model.setName((java.lang.String) entity.getProperty("name"));
        model.setOrganization((java.lang.String) entity.getProperty("organization"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        net.bcglex.gproto.model.AdmUser m = (net.bcglex.gproto.model.AdmUser) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("domain", m.getDomain());
        entity.setProperty("email", m.getEmail());
        entity.setProperty("location", m.getLocation());
        entity.setProperty("name", m.getName());
        entity.setProperty("organization", m.getOrganization());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        net.bcglex.gproto.model.AdmUser m = (net.bcglex.gproto.model.AdmUser) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        net.bcglex.gproto.model.AdmUser m = (net.bcglex.gproto.model.AdmUser) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        net.bcglex.gproto.model.AdmUser m = (net.bcglex.gproto.model.AdmUser) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        net.bcglex.gproto.model.AdmUser m = (net.bcglex.gproto.model.AdmUser) model;
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
        net.bcglex.gproto.model.AdmUser m = (net.bcglex.gproto.model.AdmUser) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getDomain() != null){
            writer.setNextPropertyName("domain");
            encoder0.encode(writer, m.getDomain());
        }
        if(m.getEmail() != null){
            writer.setNextPropertyName("email");
            encoder0.encode(writer, m.getEmail());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getLocation() != null){
            writer.setNextPropertyName("location");
            encoder0.encode(writer, m.getLocation());
        }
        if(m.getName() != null){
            writer.setNextPropertyName("name");
            encoder0.encode(writer, m.getName());
        }
        if(m.getOrganization() != null){
            writer.setNextPropertyName("organization");
            encoder0.encode(writer, m.getOrganization());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected net.bcglex.gproto.model.AdmUser jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        net.bcglex.gproto.model.AdmUser m = new net.bcglex.gproto.model.AdmUser();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("domain");
        m.setDomain(decoder0.decode(reader, m.getDomain()));
        reader = rootReader.newObjectReader("email");
        m.setEmail(decoder0.decode(reader, m.getEmail()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("location");
        m.setLocation(decoder0.decode(reader, m.getLocation()));
        reader = rootReader.newObjectReader("name");
        m.setName(decoder0.decode(reader, m.getName()));
        reader = rootReader.newObjectReader("organization");
        m.setOrganization(decoder0.decode(reader, m.getOrganization()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}