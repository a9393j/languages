import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Test class for System Design topic problems
 */
public class SystemDesignTests {
    
    @Test
    public void testLoadBalancer() {
        List<String> servers = Arrays.asList("server1", "server2", "server3");
        SystemDesignQuickNotes.LoadBalancer lb = new SystemDesignQuickNotes.LoadBalancer(servers);
        
        String server1 = lb.getServer();
        String server2 = lb.getServer();
        String server3 = lb.getServer();
        String server4 = lb.getServer();
        
        assertEquals("server1", server1);
        assertEquals("server2", server2);
        assertEquals("server3", server3);
        assertEquals("server1", server4); // Round-robin
    }
    
    @Test
    public void testLRUCache() {
        SystemDesignQuickNotes.LRUCache cache = new SystemDesignQuickNotes.LRUCache(3);
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        
        assertEquals("value1", cache.get("key1"));
        assertEquals("value2", cache.get("key2"));
        assertEquals("value3", cache.get("key3"));
        
        cache.put("key4", "value4"); // This should evict key1
        assertNull(cache.get("key1"));
        assertEquals("value4", cache.get("key4"));
    }
    
    @Test
    public void testMessageQueue() {
        SystemDesignQuickNotes.MessageQueue mq = new SystemDesignQuickNotes.MessageQueue(5);
        assertTrue(mq.enqueue("message1"));
        assertTrue(mq.enqueue("message2"));
        assertTrue(mq.enqueue("message3"));
        
        assertEquals(3, mq.size());
        assertEquals("message1", mq.dequeue());
        assertEquals("message2", mq.dequeue());
        assertEquals("message3", mq.dequeue());
        assertTrue(mq.isEmpty());
    }
}
