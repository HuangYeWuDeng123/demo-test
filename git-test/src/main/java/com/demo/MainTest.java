package com.demo;

import com.util.PowerJSON;
import org.junit.Test;
import sun.misc.BASE64Encoder;
import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

/**
 * @Author apple
 * @Date 2021/11/6 22:05
 * @Description //TODO
 **/
@Deprecated
public class MainTest {

    private String v5;

    public static void main(String[] args) throws Exception {
        // System.out.println("test1");
        //
        // System.out.println("test2");
        //
        // System.out.println("test3");
        //
        // System.out.println("hot-fix1 commit1 change");
        //
        // System.out.println("hot-fix1 commit2");
        //
        // System.out.println("hot-fix1 commit3");

        // List<String> list = new ArrayList<>();
        // list.add("a");
        // list.add("b");
        // list.add("c");
        //
        // List<String> addList = new ArrayList<>();
        // addList.add("11");
        // addList.add("22");
        // list.addAll(0, addList);
        //
        // list.forEach(System.out::println);
        // String aa = "{\"copyActionModel\":{\"procId\":\"test111\",\"procName\":\"测试111\"},\"process\":{\"name\":\"子流程3\",\"matchRule\":\"{'200020'}.indexOf(#root.sceneType)!= -1\",\"id\":\"200020\",\"cancelAction\":\"\",\"clazz\":\"process\",\"procType\":\"subOrderProduce\"},\"graph\":{\"nodes\":[{\"cancelable\":\"0\",\"shape\":\"end-node\",\"size\":[30,30],\"x\":\"1085.5\",\"y\":\"362.5\",\"id\":\"end1614753476354\",\"label\":\"结束\",\"clazz\":\"end\"},{\"cancelable\":\"0\",\"shape\":\"inclusive-gateway-node\",\"size\":[40,40],\"x\":\"179\",\"y\":\"258\",\"id\":\"inclusiveGateway1614753437280\",\"label\":\"实名制网关\",\"clazz\":\"inclusiveGateway\"},{\"cancelable\":\"0\",\"shape\":\"inclusive-gateway-node\",\"size\":[40,40],\"x\":\"306.5\",\"y\":\"258\",\"id\":\"inclusiveGateway1614753441214\",\"label\":\"无纸化网关\",\"clazz\":\"inclusiveGateway\"},{\"cancelable\":\"0\",\"shape\":\"inclusive-gateway-node\",\"size\":[40,40],\"x\":\"793\",\"y\":\"257\",\"id\":\"inclusiveGateway1614753466768\",\"label\":\"白卡网关\",\"clazz\":\"inclusiveGateway\"},{\"cancelable\":\"0\",\"shape\":\"java-task-node\",\"size\":[80,44],\"x\":\"443\",\"y\":\"258\",\"id\":\"javaTask1614753452167\",\"label\":\"预提交\",\"clazz\":\"javaTask\",\"actions\":[{\"retryable\":\"1\",\"manualOrderly\":\"0\",\"endAction\":\"1\",\"javaClass\":\"orderPreSubmitService\",\"rollbackable\":\"0\",\"actionName\":\"cb预提交\"}]},{\"cancelable\":\"0\",\"shape\":\"java-task-node\",\"size\":[80,44],\"x\":\"618\",\"y\":\"258\",\"id\":\"javaTask1614753453382\",\"label\":\"正式提交\",\"clazz\":\"javaTask\",\"actions\":[{\"retryable\":\"1\",\"manualOrderly\":\"0\",\"endAction\":\"1\",\"javaClass\":\"orderNormalSubmitService\",\"rollbackable\":\"0\",\"actionName\":\"cb正式提交\"}]},{\"cancelable\":\"0\",\"shape\":\"java-task-node\",\"size\":[80,44],\"x\":\"441.5\",\"y\":\"395.5\",\"id\":\"javaTask1614753454649\",\"label\":\"号码选占\",\"clazz\":\"javaTask\",\"actions\":[{\"retryable\":\"1\",\"manualOrderly\":\"0\",\"endAction\":\"1\",\"javaClass\":\"occupyNumberService\",\"rollbackable\":\"0\",\"actionName\":\"号码选占\"}]},{\"cancelable\":\"0\",\"shape\":\"java-task-node\",\"size\":[80,44],\"x\":\"1085\",\"y\":\"238\",\"id\":\"javaTask1614753472695\",\"label\":\"卡数据同步\",\"clazz\":\"javaTask\",\"actions\":[{\"retryable\":\"1\",\"manualOrderly\":\"0\",\"endAction\":\"1\",\"javaClass\":\"cardInfoSyncService\",\"rollbackable\":\"0\",\"actionName\":\"卡数据同步\"}]},{\"cancelable\":\"0\",\"shape\":\"manual-task-node\",\"size\":[80,44],\"x\":\"306.5\",\"y\":\"356.5\",\"id\":\"manualTask1614753447464\",\"label\":\"人工操作节点\",\"clazz\":\"manualTask\",\"actions\":[{\"retryable\":\"1\",\"manualOrderly\":\"1\",\"endAction\":\"1\",\"repeatable\":\"0\",\"jsClass\":\"paperlessExecutor\",\"rollbackable\":\"0\",\"actionName\":\"无纸化签名\"}]},{\"cancelable\":\"0\",\"shape\":\"manual-task-node\",\"size\":[80,44],\"x\":\"914.5\",\"y\":\"260.5\",\"id\":\"manualTask1614753469768\",\"label\":\"写卡\",\"clazz\":\"manualTask\",\"contentRender\":\"writeCardNode\",\"actions\":[{\"retryable\":\"1\",\"manualOrderly\":\"1\",\"endAction\":\"1\",\"repeatable\":\"1\",\"jsClass\":\"writeCardNode\",\"rollbackable\":\"0\",\"actionName\":\"写卡\"}]},{\"cancelable\":\"0\",\"shape\":\"manual-task-node\",\"size\":[80,44],\"x\":\"178.5\",\"y\":\"356.5\",\"id\":\"manualTask1614753541360\",\"label\":\"客户认证\",\"clazz\":\"manualTask\",\"actions\":[{\"retryable\":\"1\",\"manualOrderly\":\"1\",\"endAction\":\"1\",\"repeatable\":\"0\",\"jsClass\":\"custAuthExecutor\",\"rollbackable\":\"0\",\"actionName\":\"客户认证\"}]},{\"cancelable\":\"0\",\"shape\":\"start-node\",\"size\":[30,30],\"x\":\"87\",\"y\":\"256\",\"id\":\"start1614753432214\",\"label\":\"开始\",\"clazz\":\"start\"}],\"edges\":[{\"endPoint\":{\"x\":178.5,\"y\":334,\"index\":0},\"sourceAnchor\":\"2\",\"targetAnchor\":\"0\",\"conditionExpression\":\"runtime.isReal.0\",\"shape\":\"flow-polyline-round\",\"startPoint\":{\"x\":179,\"y\":276.5,\"index\":2},\"source\":\"inclusiveGateway1614753437280\",\"label\":\"未实名\",\"clazz\":\"flow\",\"target\":\"manualTask1614753541360\"},{\"endPoint\":{\"x\":288,\"y\":258,\"index\":3},\"sourceAnchor\":\"1\",\"targetAnchor\":\"3\",\"conditionExpression\":\"runtime.isReal.1\",\"shape\":\"flow-polyline-round\",\"startPoint\":{\"x\":197.5,\"y\":258,\"index\":1},\"source\":\"inclusiveGateway1614753437280\",\"label\":\"已实名\",\"clazz\":\"flow\",\"target\":\"inclusiveGateway1614753441214\"},{\"endPoint\":{\"x\":306.5,\"y\":334,\"index\":0},\"sourceAnchor\":\"2\",\"targetAnchor\":\"0\",\"conditionExpression\":\"inst.paperlessSwitch.1&&inst.uuid.null\",\"shape\":\"flow-polyline-round\",\"startPoint\":{\"x\":306.5,\"y\":276.5,\"index\":2},\"source\":\"inclusiveGateway1614753441214\",\"label\":\"需签名\",\"clazz\":\"flow\",\"target\":\"manualTask1614753447464\"},{\"endPoint\":{\"x\":402.5,\"y\":258,\"index\":3},\"sourceAnchor\":\"1\",\"targetAnchor\":\"3\",\"conditionExpression\":\"!inst.uuid.null,inst.paperlessSwitch.0\",\"shape\":\"flow-polyline-round\",\"startPoint\":{\"x\":325,\"y\":258,\"index\":1},\"source\":\"inclusiveGateway1614753441214\",\"label\":\"不需签名\",\"clazz\":\"flow\",\"target\":\"javaTask1614753452167\"},{\"endPoint\":{\"x\":874,\"y\":260.5,\"index\":3},\"sourceAnchor\":\"1\",\"targetAnchor\":\"3\",\"conditionExpression\":\"inst.remoteTag.1\",\"shape\":\"flow-polyline-round\",\"startPoint\":{\"x\":811.5,\"y\":257,\"index\":1},\"source\":\"inclusiveGateway1614753466768\",\"label\":\"白卡\",\"clazz\":\"flow\",\"target\":\"manualTask1614753469768\"},{\"endPoint\":{\"x\":1070,\"y\":362.5,\"index\":2},\"sourceAnchor\":\"2\",\"targetAnchor\":\"2\",\"conditionExpression\":\"inst.remoteTag.null\",\"shape\":\"flow-polyline-round\",\"startPoint\":{\"x\":793,\"y\":275.5,\"index\":2},\"source\":\"inclusiveGateway1614753466768\",\"label\":\"成卡\",\"clazz\":\"flow\",\"target\":\"end1614753476354\"},{\"endPoint\":{\"x\":577.5,\"y\":258,\"index\":3},\"sourceAnchor\":\"1\",\"targetAnchor\":\"3\",\"conditionExpression\":\"finish\",\"shape\":\"flow-polyline-round\",\"startPoint\":{\"x\":483.5,\"y\":258,\"index\":1},\"source\":\"javaTask1614753452167\",\"label\":\"预提交成功\",\"clazz\":\"flow\",\"target\":\"javaTask1614753453382\"},{\"endPoint\":{\"x\":441.5,\"y\":373,\"index\":0},\"sourceAnchor\":\"2\",\"targetAnchor\":\"0\",\"conditionExpression\":\"reNumberOccupy\",\"shape\":\"flow-polyline-round\",\"startPoint\":{\"x\":443,\"y\":280.5,\"index\":2},\"source\":\"javaTask1614753452167\",\"label\":\"号卡错误\",\"clazz\":\"flow\",\"target\":\"javaTask1614753454649\"},{\"endPoint\":{\"x\":774.5,\"y\":257,\"index\":3},\"sourceAnchor\":\"1\",\"targetAnchor\":\"3\",\"conditionExpression\":\"finish\",\"shape\":\"flow-polyline-round\",\"startPoint\":{\"x\":658.5,\"y\":258,\"index\":1},\"source\":\"javaTask1614753453382\",\"label\":\"正式提交成功\",\"clazz\":\"flow\",\"target\":\"inclusiveGateway1614753466768\"},{\"endPoint\":{\"x\":443,\"y\":235.5,\"index\":0},\"sourceAnchor\":\"0\",\"targetAnchor\":\"0\",\"conditionExpression\":\"orderNormalSubmitOvertime\",\"shape\":\"flow-polyline-round\",\"startPoint\":{\"x\":618,\"y\":235.5,\"index\":0},\"source\":\"javaTask1614753453382\",\"label\":\"正式提交超时\",\"clazz\":\"flow\",\"target\":\"javaTask1614753452167\"},{\"endPoint\":{\"x\":443,\"y\":280.5,\"index\":2},\"sourceAnchor\":\"1\",\"targetAnchor\":\"2\",\"conditionExpression\":\"finish\",\"shape\":\"flow-polyline-round\",\"startPoint\":{\"x\":482,\"y\":395.5,\"index\":1},\"source\":\"javaTask1614753454649\",\"label\":\"重新选占成功\",\"clazz\":\"flow\",\"target\":\"javaTask1614753452167\"},{\"endPoint\":{\"x\":1085.5,\"y\":347,\"index\":0},\"sourceAnchor\":\"2\",\"targetAnchor\":\"0\",\"conditionExpression\":\"finish\",\"shape\":\"flow-polyline-round\",\"startPoint\":{\"x\":1085,\"y\":260.5,\"index\":2},\"source\":\"javaTask1614753472695\",\"label\":\"卡数据同步成功\",\"clazz\":\"flow\",\"target\":\"end1614753476354\"},{\"endPoint\":{\"x\":914.5,\"y\":238,\"index\":0},\"sourceAnchor\":\"0\",\"targetAnchor\":\"0\",\"conditionExpression\":\"backToReWriteCard\",\"shape\":\"flow-polyline-round\",\"startPoint\":{\"x\":1085,\"y\":215.5,\"index\":0},\"source\":\"javaTask1614753472695\",\"label\":\"卡资源错误\",\"clazz\":\"flow\",\"target\":\"manualTask1614753469768\"},{\"endPoint\":{\"x\":443,\"y\":235.5,\"index\":0},\"sourceAnchor\":\"0\",\"targetAnchor\":\"0\",\"conditionExpression\":\"backToRePreSubmit\",\"shape\":\"flow-polyline-round\",\"startPoint\":{\"x\":1085,\"y\":215.5,\"index\":0},\"source\":\"javaTask1614753472695\",\"label\":\"数据相关异常\",\"clazz\":\"flow\",\"target\":\"javaTask1614753452167\"},{\"endPoint\":{\"x\":402.5,\"y\":258,\"index\":3},\"sourceAnchor\":\"1\",\"targetAnchor\":\"3\",\"conditionExpression\":\"!inst.uuid.null\",\"shape\":\"flow-polyline-round\",\"startPoint\":{\"x\":347,\"y\":356.5,\"index\":1},\"source\":\"manualTask1614753447464\",\"label\":\"已签名\",\"clazz\":\"flow\",\"target\":\"javaTask1614753452167\"},{\"endPoint\":{\"x\":1044.5,\"y\":238,\"index\":3},\"sourceAnchor\":\"1\",\"targetAnchor\":\"3\",\"conditionExpression\":\"*\",\"shape\":\"flow-polyline-round\",\"startPoint\":{\"x\":955,\"y\":260.5,\"index\":1},\"source\":\"manualTask1614753469768\",\"clazz\":\"flow\",\"target\":\"javaTask1614753472695\"},{\"endPoint\":{\"x\":288,\"y\":258,\"index\":3},\"sourceAnchor\":\"1\",\"targetAnchor\":\"3\",\"conditionExpression\":\"runtime.authentication.success\",\"shape\":\"flow-polyline-round\",\"startPoint\":{\"x\":219,\"y\":356.5,\"index\":1},\"source\":\"manualTask1614753541360\",\"label\":\"认证成功\",\"clazz\":\"flow\",\"target\":\"inclusiveGateway1614753441214\"},{\"endPoint\":{\"x\":160.5,\"y\":258,\"index\":3},\"sourceAnchor\":\"1\",\"targetAnchor\":\"3\",\"conditionExpression\":\"*\",\"shape\":\"flow-polyline-round\",\"startPoint\":{\"x\":102.5,\"y\":256,\"index\":1},\"source\":\"start1614753432214\",\"clazz\":\"flow\",\"target\":\"inclusiveGateway1614753437280\"}],\"groups\":[]}}";
        // PowerJSON allData = PowerJSON.toPowerJSON(aa);
        // PowerJSON process = allData.getPowerJSON("process");
        // PowerJSON graph = allData.getPowerJSON("graph");
        // PowerJSON copyActionModel = allData.getPowerJSON("copyActionModel");
        // process.replace("id", copyActionModel.getString("procId"));
        // process.replace("name", copyActionModel.getString("procName"));
        // long timeStamp = System.currentTimeMillis();
        // List<PowerJSON> nodes = graph.getPowerList("nodes");
        // List<PowerJSON> edges = graph.getPowerList("edges");
        // for (int i = 0, nodesSize = nodes.size(); i < nodesSize; i++) {
        //     PowerJSON node = nodes.get(i);
        //     String clazz = node.getString("clazz");
        //     String oldId = node.getString("id");
        //     String newId = clazz + timeStamp;
        //     node.put("id", newId);
        //     edges.forEach(s -> s.replace("source", oldId, newId));
        //     edges.forEach(s -> s.replace("target", oldId, newId));
        //     timeStamp++;
        // }
        // System.out.println(allData);

        // String base64File = encodeBase64File("C:\\Users\\86181\\Documents\\WeChat Files\\wxid_9gwb3sl3y1ee22\\FileStorage\\File\\2022-02\\中国联通信息化大厦(1).m4a");
        // String base64File = getBase64FromInputStream("C:\\Users\\86181\\Documents\\WeChat Files\\wxid_9gwb3sl3y1ee22\\FileStorage\\File\\2022-02\\中国联通信息化大厦(1).m4a");
        //
        // System.out.println(base64File);

        // Double aDouble = Double.valueOf(System.getProperty("java.version").substring(0, 3));
        // System.out.println(aDouble > 1.7);

        String certCode = "41052119980813405X";
        System.out.println(Pattern.matches("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)", certCode));
    }

    public static String getBase64FromInputStream(String path) throws FileNotFoundException {
        File file = new File(path);
        FileInputStream in = new FileInputStream(file);
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int rc = 0;
            while ((rc = in.read(buff, 0, buff.length)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();
        } catch (IOException e) {
            System.out.println("获取图片信息出错");
            return "";
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        String str = new String(Base64.encodeBase64(data));
        return str;
    }

    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }




    @Test
    public void test1() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.replaceAll(integer -> integer * 2);
        list.forEach(System.out::println);

        // list.sort((Comparator.comparingInt(o -> o)));

        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
    }

    @Test
    public void testToArray() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        Object[] objects1 = list.toArray();
        Arrays.stream(objects1).forEach(System.out::println);
        Object[] objects2 = list.toArray(new String[0]);
        Arrays.stream(objects2).forEach(System.out::println);
        Object[] objects3 = list.toArray(new String[6]);
        Arrays.stream(objects3).forEach(System.out::println);
    }

    @Test
    public void removeAll() {
        // List<String> list = new ArrayList<>();
        // list.add("a");
        // list.add("a");
        // list.add("c");
        // list.add("d");
        // list.add("e");
        // List<String> list1 = new ArrayList<>();
        // list1.add("a");
        // list.removeAll(list1);
        // list.forEach(System.out::println);

        System.out.println(MainTest.class.isAnnotationPresent(Deprecated.class));


    }
}
