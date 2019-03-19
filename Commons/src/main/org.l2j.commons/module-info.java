module org.l2j.commons {
    requires java.sql;
    requires org.slf4j;
    requires com.zaxxer.hikari;
    requires transitive java.xml.bind;
    requires java.management;
    requires java.compiler;
    requires cache.api;
    requires java.desktop;
    requires primitive;

    exports org.l2j.commons.util;
    exports org.l2j.commons.xml;
    exports org.l2j.commons.crypt;
    exports org.l2j.commons.database;
    exports org.l2j.commons.database.annotation;
    exports org.l2j.commons.configuration;
    exports org.l2j.commons.threading;
    exports org.l2j.commons.cache;
    exports org.l2j.commons.database.handler;
    exports org.l2j.commons.network;
    exports org.l2j.commons.util.filter;

    uses org.l2j.commons.database.handler.TypeHandler;
    provides org.l2j.commons.database.handler.TypeHandler
        with org.l2j.commons.database.handler.IntegerHandler,
             org.l2j.commons.database.handler.LongHandler,
             org.l2j.commons.database.handler.VoidHandler,
             org.l2j.commons.database.handler.ListHandler,
             org.l2j.commons.database.handler.StringHandler,
             org.l2j.commons.database.handler.IntSetHandler,
             org.l2j.commons.database.handler.EntityHandler;
}