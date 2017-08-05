package opera;

import com.sun.deploy.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**根据输入的html页面，构造标签树
 * Created by ctis-szx on 2017/8/4.
 */
public class BuildTagTree {

    private static String FILE_PATH = "src/main/java/opera/a.html";
    private static String COMMENT_START = "<!--";
    private static String COMMENT_END = "-->";
    private static String TAG_START = "<";
    private static String TAG_END = ">";



    /*
    获取html文件
     */
    public static void getFile(){
        try {
            File file = new File(FILE_PATH);
            if(file.isFile() && file.exists()){
                FileInputStream inputStream = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = reader.readLine()) != null){
                    lineParse(line);
                }
                reader.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 对输入的行进行解析
     * @param line
     */
    public static void lineParse(String line){
        if(null == line || line.trim().equals("")){
            return;
        }

    }

    /**
     * 判断当前行是否是注释
     * @param line
     * @return
     */
    public static boolean isCommentLine(String line){
        if(line.trim().startsWith(COMMENT_START) && line.trim().endsWith(COMMENT_END)){
            return true;
        }else {
            return false;
        }
        TagTree tagTree = new TagTree();

        buildTagNode();
    }

    /**
     *根据当前行，解析出标签节点
     * @Param line 当前输入行字符串
     * */

    private static List<TagNode> buildTagNode(String line){
        List<TagNode> tagNodeList = new ArrayList<>();
        for(int i = 0; i < line.length(); i ++){
            if(TAG_START.equals(line.charAt(i))){
                //遇到标签的开头,找到标签名
                int k = i + 1;
                String tagName;
                TagNode tagNode = new TagNode();
                while(!reachEnd(line.charAt(k))){
                    k ++;
                }
                tagName = line.substring(i + 1, k);
                tagNode.setTagName(tagName);
                //如果该标签没结束，则取出标签的属性MAP
                Map<String, Object> tagPropMap = new HashMap<>();
                if(!TAG_END.equals(line.charAt(k))){
                    while(" ".equals(line.charAt(k))){
                        k++;
                    }
                    int p = k;
                    while(!reachEnd(line.charAt(p))){
                        p++;
                    }
                    String tagProp = line.substring(k, p);
                    getTagProp(tagPropMap, tagProp);
                }else {

                }
            }
        }
    }

    private static void getTagProp(Map<String, Object> tagPropMap, String keyValue){
        if(keyValue == null || tagPropMap == null){
            return;
        }

    }
    private static boolean reachEnd(char tmp){
        return " ".equals(tmp) || TAG_END.equals(tmp);
    }

    public static void main(String[] args) {
        getFile();
    }
}
