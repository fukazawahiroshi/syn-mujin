package net.bcglex.gproto.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-04-17 13:55:48")
/** */
public final class AccessTokenMeta extends org.slim3.datastore.ModelMeta<net.bcglex.gproto.model.AccessToken> {

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.AccessToken> access_token = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.AccessToken>(this, "access_token", "access_token");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.AccessToken, java.lang.Integer> expires_in = new org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.AccessToken, java.lang.Integer>(this, "expires_in", "expires_in", int.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.AccessToken, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.AccessToken, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.AccessToken, java.lang.Long> timeSaved = new org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.AccessToken, java.lang.Long>(this, "timeSaved", "timeSaved", long.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.AccessToken> token_type = new org.slim3.datastore.StringAttributeMeta<net.bcglex.gproto.model.AccessToken>(this, "token_type", "token_type");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.AccessToken, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<net.bcglex.gproto.model.AccessToken, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final AccessTokenMeta slim3_singleton = new AccessTokenMeta();

    /**
     * @return the singleton
     */
    public static AccessTokenMeta get() {
       return slim3_singleton;
    }

    /** */
    public AccessTokenMeta() {
        super("AccessToken", net.bcglex.gproto.model.AccessToken.class);
    }

    @Override
    public net.bcglex.gproto.model.AccessToken entityToModel(com.google.appengine.api.datastore.Entity entity) {
        net.bcglex.gproto.model.AccessToken model = new net.bcglex.gproto.model.AccessToken();
        model.setAccess_token((java.lang.String) entity.getProperty("access_token"));
        model.setExpires_in(longToPrimitiveInt((java.lang.Long) entity.getProperty("expires_in")));
        model.setKey(entity.getKey());
        model.setTimeSaved(longToPrimitiveLong((java.lang.Long) entity.getProperty("timeSaved")));
        model.setToken_type((java.lang.String) entity.getProperty("token_type"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        net.bcglex.gproto.model.AccessToken m = (net.bcglex.gproto.model.AccessToken) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("access_token", m.getAccess_token());
        entity.setProperty("expires_in", m.getExpires_in());
        entity.setProperty("timeSaved", m.getTimeSaved());
        entity.setProperty("token_type", m.getToken_type());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        net.bcglex.gproto.model.AccessToken m = (net.bcglex.gproto.model.AccessToken) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        net.bcglex.gproto.model.AccessToken m = (net.bcglex.gproto.model.AccessToken) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        net.bcglex.gproto.model.AccessToken m = (net.bcglex.gproto.model.AccessToken) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        net.bcglex.gproto.model.AccessToken m = (net.bcglex.gproto.model.AccessToken) model;
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
        net.bcglex.gproto.model.AccessToken m = (net.bcglex.gproto.model.AccessToken) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getAccess_token() != null){
            writer.setNextPropertyName("access_token");
            encoder0.encode(writer, m.getAccess_token());
        }
        writer.setNextPropertyName("expires_in");
        encoder0.encode(writer, m.getExpires_in());
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        writer.setNextPropertyName("timeSaved");
        encoder0.encode(writer, m.getTimeSaved());
        if(m.getToken_type() != null){
            writer.setNextPropertyName("token_type");
            encoder0.encode(writer, m.getToken_type());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected net.bcglex.gproto.model.AccessToken jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        net.bcglex.gproto.model.AccessToken m = new net.bcglex.gproto.model.AccessToken();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("access_token");
        m.setAccess_token(decoder0.decode(reader, m.getAccess_token()));
        reader = rootReader.newObjectReader("expires_in");
        m.setExpires_in(decoder0.decode(reader, m.getExpires_in()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("timeSaved");
        m.setTimeSaved(decoder0.decode(reader, m.getTimeSaved()));
        reader = rootReader.newObjectReader("token_type");
        m.setToken_type(decoder0.decode(reader, m.getToken_type()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}