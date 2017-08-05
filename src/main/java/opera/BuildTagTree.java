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
        String tagLine = line.trim();
        Stack<TagNode> tagNodeStack = new Stack<>();
        List<TagNode> tagNodeList = new ArrayList<>();
        int tagStartIndex = tagLine.indexOf(TAG_START);
        int tagEndIndex = tagLine.indexOf(TAG_END);
        if(tagEndIndex == tagLine.length() - 1){//一行只有一个标签
            TagNode tagNode = new TagNode();
            tagNode.setTagName(getTagName(tagLine));
            tagNode.setTagProperty(getTagProp(tagLine));
            tagNode.setTagValue(null);
            tagNode.setChildTagNode(null);
            tagNodeStack.push(tagNode);
        }else {//一行有多个标签

        }

    }

    private static String getTagName(String line){
        String tagName = null;
        int tagStartIndex = line.indexOf(TAG_START);
        int tagEndIndex = line.indexOf(TAG_END);
        int k = tagStartIndex;
        while(k < tagEndIndex){
            if(!" ".equals(line.charAt(k))){
                k++;
            }else {
                if(k < tagEndIndex){
                    tagName = line.substring(tagStartIndex + 1, k);
                }
            }
        }
        if(k == tagEndIndex){
            tagName = line.substring(tagStartIndex + 1, tagEndIndex);
        }
        if(tagName != null){
            return tagName;
        }else {
            throw new IllegalArgumentException("标签解释异常,line = "+line);
        }
    }

    private static Map<String, Object> getTagProp(String line){
        Map<String, Object> tagPropMap = new HashMap<>();
        String propKey = null;
        String propValue = null;
        int tagStartIndex = line.indexOf(TAG_START);
        int tagEndIndex = line.indexOf(TAG_END);
        int k = tagStartIndex;
        while(k < tagEndIndex){
            if(!" ".equals(line.charAt(k))){
                k ++;
            }else {
                if(k < tagEndIndex){
                    while (" ".equals(line.charAt(k))){
                        k++;
                    }
                    int t = k;
                    int equalIndex = line.indexOf("=", k);
                    int z = equalIndex;
                    while (!" ".equals(line.charAt(t))){
                        t ++;
                    }
                    if(t < equalIndex){
                        z = t;
                    }
                    if(equalIndex > 0 && z < tagEndIndex){
                        propKey = line.substring(k, z);
                    }else {
                        if(equalIndex < 0 || z > tagEndIndex){
                            throw new IllegalArgumentException("该行标签有误, line= "+line);
                        }
                    }
                    int quotFirstIndex = line.indexOf("\"", equalIndex);
                    int quotSecondIndex = line.indexOf("\"", quotFirstIndex);
                    if(quotFirstIndex > 0 && quotSecondIndex > 0 && quotFirstIndex < tagEndIndex && quotSecondIndex < tagEndIndex){
                        propValue = line.substring(quotFirstIndex + 1, quotSecondIndex);
                    }
                    if(propKey != null && propValue != null){
                        tagPropMap.put(propKey, propValue);
                        k = quotSecondIndex + 1;
                    }
                }
            }
        }
        return tagPropMap;
    }


    private static boolean reachEnd(char tmp){
        return " ".equals(tmp) || TAG_END.equals(tmp);
    }

    public static void main(String[] args) {
        getFile();
    }
}
