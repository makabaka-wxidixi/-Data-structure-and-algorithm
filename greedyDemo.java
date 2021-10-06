import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-10-06 9:13
 */
public class greedyDemo {
    public static void main(String[] args) {
        //创建电台，放到HashMap中
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        HashSet<String> broadcast1 = new HashSet<>();
        broadcast1.add("北京");
        broadcast1.add("上海");
        broadcast1.add("天津");
        HashSet<String> broadcast2 = new HashSet<>();
        broadcast2.add("广州");
        broadcast2.add("北京");
        broadcast2.add("深圳");
        HashSet<String> broadcast3 = new HashSet<>();
        broadcast3.add("成都");
        broadcast3.add("上海");
        broadcast3.add("杭州");
        HashSet<String> broadcast4 = new HashSet<>();
        broadcast4.add("上海");
        broadcast4.add("天津");
        HashSet<String> broadcast5 = new HashSet<>();
        broadcast5.add("杭州");
        broadcast5.add("大连");
        //存入map中去
        broadcasts.put("k1", broadcast1);
        broadcasts.put("k2", broadcast2);
        broadcasts.put("k3", broadcast3);
        broadcasts.put("k4", broadcast4);
        broadcasts.put("k5", broadcast5);
        //allAreas 用于存方所有的地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("杭州");
        allAreas.add("天津");
        allAreas.add("大连");
        //创建ArrayList，用于存放选择的电台
        ArrayList<String> selected = new ArrayList<>();
        //创建临时的集合，用于保存电台覆盖区域和未覆盖区域的交集
        HashSet<String> tempSet = new HashSet<>();
        String maxKey = null;
        while (allAreas.size() != 0) {//说明还有区域没有覆盖
            maxKey = null;
            for (String key : broadcasts.keySet()) {
                tempSet.clear();
                //电台覆盖的区域
                HashSet<String> areas = broadcasts.get(key);
                //得到交集
                tempSet.addAll(areas);
                tempSet.retainAll(allAreas);
                //判断是否要替换maxKey
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            //maxKey!=null就说明找到了一个最大覆盖区域的key，那么就入集合
            if (maxKey != null) {
                selected.add(maxKey);
                //去除已经覆盖的地区
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println(selected);
    }
}
