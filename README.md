# 手写mybatis框架
## v1.0 使用dom4j解析xml
###     当前版本只能解析xml文件，只能映射select标签

## v1.1 新增insert、update、delete操作

## v1.2 支持解析${}及动态sql标签\<if> DEMO

## v1.3 抽取为框架形式可供引入，删除test包

ResultSet resultSet = preparedStatement.executeQuery();
            Class resultTypeClass = mapperStatement.getResultTypeClass();
            //遍历结果集
            while (resultSet.next()) {
                //创建结果实例
                Object res = resultTypeClass.newInstance();
                ResultSetMetaData metaData = resultSet.getMetaData();
                //获取结果的列数
                int columnCount = metaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    //将值设置到结果对象中
                    String columnName = metaData.getColumnName(i + 1);
                    Field field = resultTypeClass.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(res, resultSet.getObject(i + 1));
                }
                //将结果对象添加到结果集合
                resultList.add(res);
            }