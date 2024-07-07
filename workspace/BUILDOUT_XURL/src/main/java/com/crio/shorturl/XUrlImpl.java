package com.crio.shorturl;
import java.util.HashMap;
import java.util.Random;
class XUrlImpl implements XUrl{
  HashMap<String,String> map1 = new HashMap<>();
  HashMap<String,String> map2 = new HashMap<>();
  HashMap<String,Integer> search = new HashMap<>();
  int count = 0;
    @Override
  public String registerNewUrl(String longUrl){
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 9;
    Random random = new Random();

    String generatedString = random.ints(leftLimit, rightLimit + 1)
      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
      .limit(targetStringLength)
      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
      .toString();
        String ans = "http://short.url/"+generatedString;
        if(map1.containsKey(longUrl)) return map1.get(longUrl);
        else{
          map1.put(longUrl,ans);
          map2.put(ans,longUrl);
        }
       
       return ans;
    }
    @Override
    public String registerNewUrl(String longUrl, String shortUrl){
       if(map2.containsKey(shortUrl)) return null;
       else{
        map1.put(longUrl,shortUrl);
        map2.put(shortUrl,longUrl);
        return shortUrl;
       } 
    }
    @Override
   public String getUrl(String shortUrl){
          count++;
         if(map2.containsKey(shortUrl)) {
           search.put(map2.get(shortUrl),search.getOrDefault(map2.get(shortUrl), 0)+1);
          return map2.get(shortUrl);
         }
         else return null;
    }
    @Override
   public Integer getHitCount(String longUrl){
        if(search.containsKey(longUrl)) {
          return search.get(longUrl);
        }
        else return 0;
    }
  public  String delete(String longUrl){
       map2.remove(map1.get(longUrl));
       map1.remove(longUrl);
       return null;
    }

}