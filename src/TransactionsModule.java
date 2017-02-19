import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by felipe on 17/2/2017.
 */
public class TransactionsModule extends Module{

    public TransactionsModule() {
        Comparator<QueryType> comparator = new QueryComparator();
        PriorityQueue<QueryType> stack = new PriorityQueue<QueryType>(comparator);
        super.setStackQueries(stack);
    }

    public class QueryComparator implements Comparator<QueryType> {

        public int compare(QueryType query1, QueryType query2) {
            return query1.getPriority() - query2.getPriority();
        }
    }
}
