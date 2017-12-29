package sample;

import java.util.List;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.CacheWriteSynchronizationMode;
import org.apache.ignite.cache.affinity.AffinityKey;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;

/**
 *
 */
public class App 
{
    /** Cache name. */
    private static final String CACHE_NAME = "SampleCache";

    /**
     * @param args Does not matter.
     */
    public static void main( String[] args )
    {
        try (Ignite ignite = Ignition.start("config/example-ignite.xml")) {
            // Auto-close cache at the end of the example.
            try (IgniteCache<Integer, OuterClass> cache = ignite.getOrCreateCache(cacheCfg())) {
                cache.put(1, new OuterClass(1, "one"));
                cache.put(2, new OuterClass(2, "two"));

                // Execute query to get names of all employees.
                QueryCursor<List<?>> cursor = cache.query(new SqlFieldsQuery(
                    "select outerId, outerName, innerId, innerName from OuterClass"));

                List<List<?>> res = cursor.getAll();

                // Print results.
                print(res);
            }
        }
    }

    /**
     * @return CacheConfiguration
     */
    private static CacheConfiguration<Integer, OuterClass> cacheCfg() {
        return new CacheConfiguration<Integer, OuterClass>()
            .setName(CACHE_NAME)
            .setIndexedTypes(Integer.class, OuterClass.class);
    }

    /**
     * Prints query results.
     *
     * @param col Query results.
     */
    private static void print(Iterable<?> col) {
        for (Object next : col)
            System.out.println(next);
    }
}
