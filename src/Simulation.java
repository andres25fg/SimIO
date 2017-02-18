/**
 * Created by Andrés on 11/2/2017.
 */

public class Simulation  {

   public static void main (String[] args){
        for (QueryType i : new QueryType[] { QueryType.DDL, QueryType.UPDATE,
                QueryType.JOIN, QueryType.SELECT}) {
            System.out.println(i.toString() + " " +
                    i.getPriority() + " " +
                    i.getReadOnly());
        }
    }
}
