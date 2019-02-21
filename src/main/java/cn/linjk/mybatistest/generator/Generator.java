package cn.linjk.mybatistest.generator;

        import org.mybatis.generator.api.MyBatisGenerator;
        import org.mybatis.generator.config.Configuration;
        import org.mybatis.generator.config.xml.ConfigurationParser;
        import org.mybatis.generator.internal.DefaultShellCallback;

        import java.io.InputStream;
        import java.util.ArrayList;
        import java.util.List;

public class Generator {
    public static void main(String[] args) throws Exception {
        // 代码生成器执行过程中的警告信息
        List<String> warnings = new ArrayList<>();
        // 当生成的代码重复时，覆盖原代码
        boolean overwrite = true;
        InputStream inputStream = Generator.class.getResourceAsStream("/generator/generatorConfig.xml");
        ConfigurationParser configurationParser = new ConfigurationParser(warnings);
        Configuration configuration = configurationParser.parseConfiguration(inputStream);
        inputStream.close();

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
}
