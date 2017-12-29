package sample;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

/**
 *
 */
public class InnerClass {
    /** Inner id. */
    @QuerySqlField(index = true)
    public Integer innerId;

    /** Inner name. */
    @QuerySqlField()
    public String innerName;
}
