package sample;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

/**
 *
 */
public class OuterClass {
    /** Id. */
    @QuerySqlField(index = true)
    public Integer outerId;

    /** Name. */
    @QuerySqlField()
    public String outerName;

    /** Inner. */
    @QuerySqlField()
    public InnerClass inner;

    /**
     * @param id Id.
     * @param name Name.
     */
    public OuterClass(Integer id, String name) {
        this.outerId = id;
        this.outerName = name;
        this.inner = new InnerClass();
        this.inner.innerId = id;
        this.inner.innerName = name;
    }
}
