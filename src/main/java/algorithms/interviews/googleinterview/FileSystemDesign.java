package algorithms.interviews.googleinterview;

import java.util.Map;

public class FileSystemDesign {

    //
}

enum Type {
    FILE, DIR
}

interface Entry {
    long getSize();
    String getName();


}

interface EntryId {}
interface FileSystem {

    Map<EntryId, Entry> getEntries();


    default long getSize(EntryId id) {
        return 0;
    }
    /* What if we keep on getting requests for entries ? How can we optimize? - We can use cache
     What is the difference between a tree and graph
       1
      / \
     2   3
     In a tree, 2 and 3 can't be connected. Meaning there can't be a cycle in tree

       1
      / \
     2   3
          \
           1
      In a file system can there be a cycle? - NO

     */

    default boolean isValid(EntryId id) { //hasCycle
        return false;
    }
}