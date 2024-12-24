package generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;

/**
 * 代码生成器
 */
public class MybatisPlusGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create(
                        "jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf-8",
                        "root",
                        "123"
                )
                .globalConfig(builder -> {
                    builder.author("zhengwei") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("D:\\java_projects\\spring-boot-demo\\mybatis-plus-demo\\src\\main\\java\\"); // 指定输出目录(项目新建时的java目录绝对路径)
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder -> {
                    builder.parent("com.zhengwei.mybatis") // 设置父包名(项目新建时，src/main/java下的包名）-> 所以应该在项目新建时使用这个generator生成代码
                            .moduleName("")
                            .entity("model")
                            .mapper("dao")
                            .service("service")
                            .serviceImpl("service.impl")
                            .xml("mappers")
                            .controller("controller"); // 设置 Controller 包名
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名
                            .entityBuilder()
                            .enableLombok(); // 启用 Lombok
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute(); // 执行生成
    }

}
