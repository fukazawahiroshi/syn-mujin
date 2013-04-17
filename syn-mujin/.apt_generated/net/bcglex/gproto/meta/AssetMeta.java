package net.bcglex.gproto.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-04-17 13:55:48")
/** */
public final class AssetMeta extends org.slim3.datastore.ModelMeta<net.bcglex.gproto.model.Asset> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.Asset, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.Asset, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Asset> password = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.Asset>(this, "password", "password");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.Asset, java.lang.Long> utime = new org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.Asset, java.lang.Long>(this, "utime", "utime", long.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.Asset, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.Asset, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final AssetMeta slim3_singleton = new AssetMeta();

    /**
     * @return the singleton
     */
    public static AssetMeta get() {
       return slim3_singleton;
    }

    /** */
    public AssetMeta() {
        super("Asset", net.bcglex.gproto.model.Asset.class);
    }

    @Override
    public net.bcglex.gproto.model.Asset entityToModel(com.google.appengine.api.datastore.Entity entity) {
        net.bcglex.gproto.model.Asset model = new net.bcglex.gproto.model.Asset();
        model.setKey(entity.getKey());
        model.setPassword((java.lang.String) entity.getProperty("password"));
        model.setUtime(longToPrimitiveLong((java.lang.Long) entity.getProperty("utime")));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        net.bcglex.gproto.model.Asset m = (net.bcglex.gproto.model.Asset) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("password", m.getPassword());
        entity.setProperty("utime", m.getUtime());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        net.bcglex.gproto.model.Asset m = (net.bcglex.gproto.model.Asset) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        net.bcglex.gproto.model.Asset m = (net.bcglex.gproto.model.Asset) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        net.bcglex.gproto.model.Asset m = (net.bcglex.gproto.model.Asset) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        net.bcglex.gproto.model.Asset m = (net.bcglex.gproto.model.Asset) model;
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
        net.bcglex.gproto.model.Asset m = (net.bcglex.gproto.model.Asset) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getPassword() != null){
            writer.setNextPropertyName("password");
            encoder0.encode(writer, m.getPassword());
        }
        writer.setNextPropertyName("utime");
        encoder0.encode(writer, m.getUtime());
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected net.bcglex.gproto.model.Asset jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        net.bcglex.gproto.model.Asset m = new net.bcglex.gproto.model.Asset();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("password");
        m.setPassword(decoder0.decode(reader, m.getPassword()));
        reader = rootReader.newObjectReader("utime");
        m.setUtime(decoder0.decode(reader, m.getUtime()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}