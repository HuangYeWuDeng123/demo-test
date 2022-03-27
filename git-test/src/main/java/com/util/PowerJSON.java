package com.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

/**
 * <p>可做Streaming操作的json扩展，比如使用powerJson.put(key, value).put(key,value).sPutAll(mapObj).putIfNotEmpty(key, value)</p>
 * 可进行json对象的层级选择，如{"rsp":{"OrderResp":{"someKey":"someValue"}}}，可使用powerJson.selectString("rsp.OrderResp.someKey")进行选择
 *
 * @Author mengqk
 * @Since [2019/5/22]
 * @Version 1.0
 */
public class PowerJSON extends JSONObject {
	public PowerJSON() {
		super();
	}

	public PowerJSON(Map<String, Object> map) {
		super(map);
	}

	public PowerJSON(boolean ordered) {
		super(ordered);
	}

	public PowerJSON(int initialCapacity) {
		super(initialCapacity);
	}

	public PowerJSON(int initialCapacity, boolean ordered) {
		super(initialCapacity, ordered);
	}

	public PowerJSON(String jsonStr) {
		this(JSON.parseObject(jsonStr));
	}

	/**
	 * <p>重写put方法，返回当前对象以进行streaming操作</p>
	 *
	 * @param key
	 * @param value
	 * @author mengqk
	 * @version 1.0
	 * @since [2019/5/22]
	 */
	@Override
	public PowerJSON put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	/**
	 * <p>新增s系列put方法，返回当前对象以进行streaming操作</p>
	 *
	 * @author mengqk
	 * @version 1.0
	 * @since [2019/5/22]
	 */
	public PowerJSON sput(String key, Object value) {
		return put(key, value);
	}

	/**
	 * <p>新增s系列putAll方法，返回当前对象以进行streaming操作</p>
	 *
	 * @author mengqk
	 * @version 1.0
	 * @since [2019/5/22]
	 */
	public PowerJSON sputAll(Map<? extends String, ? extends Object> m) {
		putAll(m);
		return this;
	}

	/**
	 * <p>新增方法，当value非空的时候才做put，返回当前对象以进行streaming操作</p>
	 *
	 * @author mengqk
	 * @version 1.0
	 * @since [2019/5/22]
	 */
	public PowerJSON putIfNotEmpty(String key, Object value) {
		if (isEmpty(value)) {
			return this;
		}
		return put(key, value);
	}

	/**
	 * <p>新增方法，当value非空白的时候才做put，返回当前对象以进行streaming操作</p>
	 *
	 * @author mengqk
	 * @version 1.0
	 * @since [2019/5/22]
	 */
	public PowerJSON putIfNotBlank(String key, Object value) {
		if (null == value) {
			return this;
		}
		if (value instanceof String) {
			if (isBlank((String) value)) {
				return this;
			}
		}
		return put(key, value);
	}

	/**
	 * <p>新增方法，当value和比较值不相等时才put，返回当前对象以进行streaming操作</p>
	 *
	 * @author mengqk
	 * @version 1.0
	 * @since [2019/5/22]
	 */
	public PowerJSON putIfNotEquals(String key, Object value, Object compare) {
		if (value == compare) {
			return this;
		}
		if (null == value || !value.equals(compare)) {
			return put(key, value);
		}
		return this;
	}

	/**
	 * <p>从一个map中复制一份相同的key/value</p>
	 *
	 * @param key
	 * @param src
	 * @author mengqk
	 * @version 1.0
	 * @since [2019/5/23]
	 */
	public PowerJSON copyFrom(Map<String, Object> src, String key) {
		return put(key, src.get(key));
	}

	/**
	 * <p>从一个map中复制一份相同的key/value，空值过滤</p>
	 *
	 * @param key
	 * @param src
	 * @author mengqk
	 * @version 1.0
	 * @since [2019/5/23]
	 */
	public PowerJSON copyFromNotEmpty(Map<String, Object> src, String key) {
		Object value = src.get(key);
		if (isEmpty(value)) {
			return this;
		}
		return put(key, value);
	}

	/**
	 * <p>根据选择表达式复制一份相同的key/value</p>
	 *
	 * @param selector
	 * @param src
	 * @author mengqk
	 * @version 1.0
	 * @since [2019/5/23]

	public PowerJSON copyFromSelector(PowerJSON src, String selector) {
	if (selector.indexOf(".") > 0) {
	int split = selector.lastIndexOf(".");
	String objSelector = selector.substring(0, split);
	String key = selector.substring(split + 1);
	Map<String, Object> value = (Map<String, Object>) this.selectValue(objSelector);
	value.put(key, src.selectValue(selector));
	return this;
	} else {
	return copyFrom(src, selector);
	}
	}*/

	/**
	 * <p>超级json转换</p>
	 *
	 * @author mengqk
	 * @version 1.0
	 * @since [2019/5/22]
	 */
	public static PowerJSON toPowerJSON(Object object) {
		if (null == object) {
			return null;
		}
		if (object instanceof PowerJSON) {
			return (PowerJSON) object;
		}
		if (object instanceof Map) {
			return new PowerJSON((Map<String, Object>) object);
		}
		if (object instanceof String) {
			if (isEmpty((String) object)) {
				return new PowerJSON();
			}
			return new PowerJSON((String) object);
		}
		throw new RuntimeException("不支持的数据类型：" + object.getClass());
	}

	/**
	 * <p>获取PowerJSON列表进行处理</p>
	 *
	 * @param key
	 * @author mengqk
	 * @since [2019/7/25]
	 * @version 1.0
	 */
	public List<PowerJSON> getPowerList(String key) {
		Object value = get(key);
		if (null == value) {
			return new ArrayList<PowerJSON>();
		}
		if (value instanceof List) {
			return toPowerArray((List<?>) value);
		} else {
			return toPowerArray(value instanceof String ? (JSONArray)JSON.parse((String)value) : (JSONArray)toJSON(value));
		}
	}

	private List<PowerJSON> toPowerArray(List<?> value) {
		if (isEmpty(value)) {
			return new ArrayList<PowerJSON>();
		}
		List<PowerJSON> result = new ArrayList<PowerJSON>(value.size());
		PowerJSON powerJSON;
		for (Object obj : value) {
			powerJSON = toPowerJSON(obj);
			if (null != powerJSON) {
				result.add(toPowerJSON(obj));
			}
		}
		return result;
	}

	/**
	 * <p>在指定选择器下工作，返回选择之后的对象</p>
	 *
	 * @param selector
	 * @return com.chinaunicom.cbss2.beeaction.bean.base.PowerJSON
	 * @author mengqk
	 * @since [2019/6/21]
	 * @version 1.0
	 */
	public PowerJSON withStructure(String selector) {
		String[] levels = selector.split("\\.");
		PowerJSON parent = this;
		PowerJSON _this = parent;
		for (String level : levels) {
			_this = parent.getPowerJSON(level);
			if (null == _this) {
				_this = new PowerJSON();
				parent.put(level, _this);
			}
			parent = _this;
		}
		return _this;
	}


	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("giaogiao");
		PowerJSON powerJSON = new PowerJSON();
		powerJSON.put("array",new int[]{4,5,6});
		HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
		HashMap<Object, Object> obj = new HashMap<>();
		obj.put("giao","4");
		objectObjectHashMap.put("out",obj);
		powerJSON.put("map",objectObjectHashMap);
		powerJSON.put("giao",list);

		Object eeee = powerJSON.findPathMatchingResources("giao",powerJSON);
		System.out.println(eeee);
	}

	public PowerJSON copyProperties(PowerJSON fromJson,String key) {
		return this.copyProperties(fromJson,key,key); // 这个时候想要的字段在fromJson和toJson中一样的位置
	}

	public PowerJSON copyProperties(PowerJSON fromJson,String fromPath,String key) {
		return this.put(key,findPathMatchingResources(fromPath,fromJson));
	}

    // 如果知道自己要的是string，且空字符串和Null都返回""，可以用它
    // public String findPathMatchingString(String locationPattern,Object root) {
    //     Object pathMatchingResources = findPathMatchingResources(locationPattern, root);
    //     return ObjectUtils.getDisplayString(pathMatchingResources);
    // }

    /**
     * 同时结合了withStructure和SelectString的功能，且修复了其不支持PowerList，数组、列表、基础类型的bug
     * @param locationPattern 同withStructure和SelectString，支持a.b.0.c
     * @param root
     * @return
     */
	public Object findPathMatchingResources(String locationPattern,Object root) {
		String[] selectors = locationPattern.split("\\.");
		Queue<String> queue = new LinkedList<>(Arrays.asList(selectors));
		return doFindPathMatchingResources(root,queue);
	}

	private Object doFindPathMatchingResources(Object object, Queue<String> queue) {
		String selector = queue.poll();
		if (isMap(object) && !isNumber(selector)) {
			if (queue.size() == 0) {
                return ((Map<?,?>)object).get(selector);
            } else {
                return doFindPathMatchingResources(((Map<?,?>)object).get(selector),queue);
            }
		}

		if (isList(object) && isNumber(selector)) {
			if (queue.size() == 0) {
                return ((List<?>)object).get(Integer.parseInt(selector));
            } else {
                return doFindPathMatchingResources(((List<?>)object).get(Integer.parseInt(selector)),queue);
            }
		}

		if (isArray(object) && isNumber(selector)) {
			if (queue.size() == 0) {
				if (object instanceof Object[]) {
					return ((Object[])object)[Integer.parseInt(selector)];
				}

				if (object instanceof boolean[]) {
					return new Boolean(((boolean[])object)[Integer.parseInt(selector)]);
				}

				if (object instanceof byte[]) {
					return new Byte(((byte[])object)[Integer.parseInt(selector)]);
				}

				if (object instanceof char[]) {
					return new Character(((char[])object)[Integer.parseInt(selector)]);
				}

				if (object instanceof double[]) {
					return new Double(((double[])object)[Integer.parseInt(selector)]);
				}

				if (object instanceof float[]) {
					return new Float(((float[])object)[Integer.parseInt(selector)]);
				}

				if (object instanceof int[]) {
					return new Integer(((int[])object)[Integer.parseInt(selector)]);
				}

				if (object instanceof long[]) {
					return new Long(((long[])object)[Integer.parseInt(selector)]);
				}

				if (object instanceof short[]) {
					return new Short(((short[])object)[Integer.parseInt(selector)]);
				}
			} else {
				return null;
			}
		}

		if (isString(object) && !isNumber(selector)) {
			if (queue.size() == 0) {
                return object;
            } else {
				try {
					JSONObject root = JSONObject.parseObject((String) object);
					return doFindPathMatchingResources(root,queue);
				}catch (Exception e) {
					return null;
				}
			}
		}

		return null;
	}

	private boolean isNumber(String selector) {
		try {
			Integer.parseInt(selector);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	private boolean isString(Object root) {
		return root instanceof String;
	}

	private boolean isPrimitive(Object root) {
		return root.getClass().isPrimitive();
	}

	private boolean isArray(Object root) {
		return root.getClass().isArray();
	}

	private boolean isList(Object selectorInstance) {
		return selectorInstance instanceof List;
	}

	private boolean isMap(Object selectorInstance) {
		return selectorInstance instanceof Map; // 就普通map和jsonObject和powerJson都是Map接口的实现类
	}

	/**
	 * <p>取Map里层数据，形如：CustMess.Custid，List需要使用listName.0.itemName</p>
	 *
	 * @param selector
	 * @return
	 * @author mengqk
	 * @since [2016年1月14日]
	 */
	@SuppressWarnings("unchecked")
	public Object selectValue(String selector) {
		if (isEmpty(selector)) {
			return this;
		}

		// 处理表达式
		Object result = null;
		String[] level = selector.split("\\.");

		int lastIdx = level.length - 1;
		String key = null;
		Object tmpResult = this;
		boolean lastIsList = false;
		for (int i = 0; i < level.length; i++) {
			key = level[i];
			if (isEmpty(key)) {
				lastIsList = false;
				continue;
			}
			if (null == tmpResult) {
				break;
			}
			if (lastIsList) {
				JSONArray tmpList = (JSONArray) tmpResult;
				int index = toInt(key, 0);
				if (tmpList.size() > index) {
					tmpResult = tmpList.get(index);
				} else {
					break;
				}
			} else {
				if ("*".equals(key)) {
					tmpResult = ((Map<String, Object>) tmpResult).entrySet().iterator().next().getValue();
				} else {
					tmpResult = ((Map<String, Object>) tmpResult).get(key);
				}
			}
			if (tmpResult instanceof List) {
				lastIsList = true;
			} else {
				lastIsList = false;
			}
			if (i == lastIdx) {
				result = tmpResult;
			}
		}
		return result;
	}

	public Boolean selectBoolean(String selector) {
		Object value = this.selectValue(selector);
		return value == null ? null : TypeUtils.castToBoolean(value);
	}

	public byte[] selectBytes(String selector) {
		Object value = this.selectValue(selector);
		return value == null ? null : TypeUtils.castToBytes(value);
	}

	public boolean selectBooleanValue(String selector) {
		Object value = this.selectValue(selector);
		return value == null ? false : TypeUtils.castToBoolean(value);
	}

	public Byte selectByte(String selector) {
		Object value = this.selectValue(selector);
		return TypeUtils.castToByte(value);
	}

	public byte selectByteValue(String selector) {
		Object value = this.selectValue(selector);
		return value == null ? 0 : TypeUtils.castToByte(value);
	}

	public Short selectShort(String selector) {
		Object value = this.selectValue(selector);
		return TypeUtils.castToShort(value);
	}

	public short selectShortValue(String selector) {
		Object value = this.selectValue(selector);
		return value == null ? 0 : TypeUtils.castToShort(value);
	}

	public Integer selectInteger(String selector) {
		Object value = this.selectValue(selector);
		return TypeUtils.castToInt(value);
	}

	public int selectIntValue(String selector) {
		Object value = this.selectValue(selector);
		return value == null ? 0 : TypeUtils.castToInt(value);
	}

	public Long selectLong(String selector) {
		Object value = this.selectValue(selector);
		return TypeUtils.castToLong(value);
	}

	public long selectLongValue(String selector) {
		Object value = this.selectValue(selector);
		return value == null ? 0L : TypeUtils.castToLong(value);
	}

	public Float selectFloat(String selector) {
		Object value = this.selectValue(selector);
		return TypeUtils.castToFloat(value);
	}

	public float selectFloatValue(String selector) {
		Object value = this.selectValue(selector);
		return value == null ? 0.0F : TypeUtils.castToFloat(value);
	}

	public Double selectDouble(String selector) {
		Object value = this.selectValue(selector);
		return TypeUtils.castToDouble(value);
	}

	public double selectDoubleValue(String selector) {
		Object value = this.selectValue(selector);
		return value == null ? 0.0D : TypeUtils.castToDouble(value);
	}

	public BigDecimal selectBigDecimal(String selector) {
		Object value = this.selectValue(selector);
		return TypeUtils.castToBigDecimal(value);
	}

	public BigInteger selectBigInteger(String selector) {
		Object value = this.selectValue(selector);
		return TypeUtils.castToBigInteger(value);
	}

	public String selectString(String selector) {
		Object value = this.selectValue(selector);
		return value == null ? null : value.toString();
	}

	public Date selectDate(String selector) {
		Object value = this.selectValue(selector);
		return TypeUtils.castToDate(value);
	}

	public Date selectSqlDate(String selector) {
		Object value = this.selectValue(selector);
		return TypeUtils.castToSqlDate(value);
	}

	public Timestamp selectTimestamp(String selector) {
		Object value = this.selectValue(selector);
		return TypeUtils.castToTimestamp(value);
	}

	private boolean isBlank(String value) {
		int strLen;
		if (value != null && (strLen = value.length()) != 0) {
			for (int i = 0; i < strLen; ++i) {
				if (!Character.isWhitespace(value.charAt(i))) {
					return false;
				}
			}

			return true;
		} else {
			return true;
		}
	}

	private static boolean isEmpty(String value) {
		return null == value || value.isEmpty();
	}

	private int toInt(String key, int defaultValue) {
		if (key == null) {
			return defaultValue;
		} else {
			try {
				return Integer.parseInt(key);
			} catch (NumberFormatException var3) {
				return defaultValue;
			}
		}
	}

	public boolean isEmpty(Object obj) {
		if (null == obj) {
			return true;
		}
		if (obj instanceof String) {
			return ((String) obj).isEmpty();
		} else if (obj instanceof Collection) {
			return ((Collection) obj).isEmpty();
		} else if (obj instanceof Map) {
			return ((Map) obj).isEmpty();
		} else if (obj.getClass().isArray()) {
			return ((Object[]) obj).length == 0;
		}
		return false;
	}

	/**
	 * <p>指定的key对应的值是否为空</p>
	 *
	 * @param key
	 * @return boolean
	 * @author mengqk
	 * @version 1.0
	 * @since [2019/5/23]
	 */
	public boolean isKeyEmpty(String key) {
		return isEmpty(get(key));
	}

	/**
	 * <p>指定的选择表达式对应的值是否为空</p>
	 *
	 * @param selector
	 * @return boolean
	 * @author mengqk
	 * @version 1.0
	 * @since [2019/5/23]
	 */
	public boolean isSelectorEmpty(String selector) {
		return isEmpty(selectValue(selector));
	}

	@Override
	public PowerJSON remove(Object key) {
		super.remove(key);
		return this;
	}

	/**
	 * <p>根据key取powerJson对象</p>
	 *
	 * @param key
	 * @return com.chinaunicom.cbss2.beeaction.bean.base.PowerJSON
	 * @author mengqk
	 * @version 1.0
	 * @since [2019/5/27]
	 */
	public PowerJSON getPowerJSON(String key) {
		Object object = get(key);
		return toPowerJSON(object);
	}

	/***
	 * <p>put一个键值对输入</p>
	 *
	 * @param keyValuePair
	 * @author mengqk
	 * @since [2019/5/31]
	 * @version 1.0
	 */
	public PowerJSON putAll(Object... keyValuePair) {
		for (int i = 0; i < keyValuePair.length / 2; i++) {
			String key = (String) keyValuePair[i * 2];
			Object value = keyValuePair[i * 2 + 1];
			if (value != null) {
				this.put(key, value);
			}
		}
		return this;
	}

	/**
	 * 获取key对应的键值，如果value值不存在，则返回defaultValue
	 *
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String getString(String key, String defaultValue) {
		String value = getString(key);
		return null == value ? defaultValue : value;
	}
}
