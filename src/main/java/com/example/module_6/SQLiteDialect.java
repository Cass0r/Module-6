package com.example.module_6;

import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupport;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;
import org.hibernate.engine.jdbc.env.spi.NameQualifierSupport;

import java.sql.Types;

// Custom Dialect class to allow Hibernate to work with SQLite
public class SQLiteDialect extends Dialect {

// Constructor: registers the data types used by SQLite
    public SQLiteDialect() {
        super();
        // Map standard SQL types to SQLite types
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.VARCHAR, "text");
        registerColumnType(Types.BOOLEAN, "integer");
    }

// Specifies how Hibernate should support identity (auto-increment) columns in SQLite
    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new IdentityColumnSupportImpl();
    }

// Indicates that SQLite supports identity columns (auto-increment behavior)
    public boolean supportsIdentityColumns() {
        return true;
    }

// SQLite does NOT require the data type in identity columns (just "INTEGER PRIMARY KEY")
    public boolean hasDataTypeInIdentityColumn() {
        return false;
    }

// Returns the SQL query used to get the last auto-generated ID
    public String getIdentitySelectString(String table, String column, int type) throws MappingException {
        return "select last_insert_rowid()";
    }

// Specifies the data type used for identity columns
    public String getIdentityColumnString(int type) throws MappingException {
        return "integer";
    }

// Indicates that SQLite supports LIMIT in SQL queries (for pagination)
    @Override
    public boolean supportsLimit() {
        return true;
    }

// Returns SQL syntax for applying a LIMIT (with optional OFFSET)
    @Override
    public String getLimitString(String query, boolean hasOffset) {
        return query + (hasOffset ? " limit ? offset ?" : " limit ?");
    }

// Indicates support for temporary tables in SQLite
    public boolean supportsTemporaryTables() {
        return true;
    }

// SQL syntax for creating temporary tables
    public String getCreateTemporaryTableString() {
        return "create temporary table if not exists";
    }

// Determines whether temporary tables should be dropped automatically
    public boolean dropTemporaryTableAfterUse() {
        return false;
    }

// SQLite does not support qualified names (like schema.table), so return NONE
    @Override
    public NameQualifierSupport getNameQualifierSupport() {
        return NameQualifierSupport.NONE;
    }
}
