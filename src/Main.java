import com.xmc.reflect.entity.Type;
import com.xmc.reflect.method.ReflectUtils;

public class Main {

    public static void main(String[] args) throws Exception {

        Type type = new Type();
        type.setDescription("测试类型");
        type.setName("xmc1993");
        type.setId(1);

        String name = (String)ReflectUtils.getValue(type, "name");

        System.out.println(name);

        ReflectUtils.invokeMethod(type, "whisper", "xmc");

        ReflectUtils.invokeMethod(type, "noPara");


    }
}
