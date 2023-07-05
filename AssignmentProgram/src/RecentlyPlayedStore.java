
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

public class RecentlyPlayedStore {
    private final int capacity;
    private final Map<String, LinkedList<String>> store;

    public RecentlyPlayedStore(int capacity) {
        this.capacity = capacity;
        this.store = new HashMap<>();
    }

    public void addSong(String user, String song) {
        if (store.containsKey(user)) {
            LinkedList<String> songs = store.get(user);
            songs.add(song);
            if (songs.size() > capacity) {
                songs.removeFirst(); 
            }
        } else {
            LinkedList<String> songs = new LinkedList<>();
            songs.add(song);
            store.put(user, songs);
        }
    }

    public List<String> getRecentlyPlayedSongs(String user) {
        if (store.containsKey(user)) {
            return store.get(user);
        } else {
            return Collections.emptyList();
        }
    }

    public static void main(String[] args) {
        RecentlyPlayedStore store = new RecentlyPlayedStore(3);

        
        store.addSong("User1", "Song1");
        store.addSong("User2", "Song2");
        store.addSong("User1", "Song3");
        store.addSong("User3", "Song4");
        store.addSong("User1", "Song5");

        
        List<String> songsForUser1 = store.getRecentlyPlayedSongs("User1");
        System.out.println("Recently played songs for User1:");
        for (String song : songsForUser1) {
            System.out.println(song);
        }

       
        List<String> songsForUser2 = store.getRecentlyPlayedSongs("User2");
        System.out.println("Recently played songs for User2:");
        for (String song : songsForUser2) {
            System.out.println(song);
        }

      
        List<String> songsForUser3 = store.getRecentlyPlayedSongs("User3");
        System.out.println("Recently played songs for User3:");
        for (String song : songsForUser3) {
            System.out.println(song);
        }

        
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.example.com");

        
        for (String song : songsForUser1) {
            WebElement searchInput = driver.findElement(By.name("search"));
            searchInput.sendKeys(song);
            searchInput.submit();
        }

       
        driver.get("https://www.example.com/search?user=User1");
        List<WebElement> searchResults = driver.findElements(By.className("song-title"));
        System.out.println("Recently played songs for User1 (validated with Selenium):");
        for (WebElement result : searchResults) {
            System.out.println(result.getText());
        }

        driver.quit();
    }
}

