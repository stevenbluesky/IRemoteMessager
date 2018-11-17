package cn.com.isurpass.iremotemessager.common.util;

import cn.com.isurpass.iremotemessager.vo.ExportMessageTemplateVo;
import cn.com.isurpass.iremotemessager.vo.MessageTemplateVo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liwenxiang
 * Date:2018/11/12
 * Time:16:34
 */
public class ExportExcelUtil {
    public void ExportWithResponse(String sheetName, String titleName,
                                   String fileName, int columnNumber, int[] columnWidth,
                                   String[] columnName, List<MessageTemplateVo> dataList,
                                   HttpServletResponse response) throws Exception {
        if (columnNumber == columnWidth.length&& columnWidth.length == columnName.length) {
            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet(sheetName);
            // sheet.setDefaultColumnWidth(15); //统一设置列宽
            for (int i = 0; i < columnNumber; i++)
            {
                for (int j = 0; j <= i; j++)
                {
                    if (i == j)
                    {
                        sheet.setColumnWidth(i, columnWidth[j] * 256); // 单独设置每列的宽
                    }
                }
            }
            // 创建第0行 也就是标题
            HSSFRow row1 = sheet.createRow((int) 0);
            row1.setHeightInPoints(50);// 设备标题的高度
            // 第三步创建标题的单元格样式style2以及字体样式headerFont1
            HSSFCellStyle style2 = wb.createCellStyle();
            style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            style2.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
            style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            HSSFFont headerFont1 = (HSSFFont) wb.createFont(); // 创建字体样式
            headerFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
            headerFont1.setFontName("黑体"); // 设置字体类型
            headerFont1.setFontHeightInPoints((short) 15); // 设置字体大小
            style2.setFont(headerFont1); // 为标题样式设置字体样式

            HSSFCell cell1 = row1.createCell(0);// 创建标题第一列
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,
                    columnNumber - 1)); // 合并列标题
            cell1.setCellValue(titleName); // 设置值标题
            cell1.setCellStyle(style2); // 设置标题样式

            // 创建第1行 也就是表头
            HSSFRow row = sheet.createRow((int) 1);
            row.setHeightInPoints(37);// 设置表头高度

            // 第四步，创建表头单元格样式 以及表头的字体样式
            HSSFCellStyle style = wb.createCellStyle();
            style.setWrapText(true);// 设置自动换行
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个居中格式

            style.setBottomBorderColor(HSSFColor.BLACK.index);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);

            HSSFFont headerFont = (HSSFFont) wb.createFont(); // 创建字体样式
            headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
            headerFont.setFontName("黑体"); // 设置字体类型
            headerFont.setFontHeightInPoints((short) 10); // 设置字体大小
            style.setFont(headerFont); // 为标题样式设置字体样式

            // 第四.一步，创建表头的列
            for (int i = 0; i < columnNumber; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(columnName[i]);
                cell.setCellStyle(style);
            }

            // 第五步，创建单元格，并设置值
            for (int i = 0; i < dataList.size(); i++)
            {
                row = sheet.createRow((int) i + 2);
                // 为数据内容设置特点新单元格样式1 自动换行 上下居中
                HSSFCellStyle zidonghuanhang = wb.createCellStyle();
                zidonghuanhang.setWrapText(true);// 设置自动换行
                zidonghuanhang.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个居中格式

                // 设置边框
                zidonghuanhang.setBottomBorderColor(HSSFColor.BLACK.index);
                zidonghuanhang.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                zidonghuanhang.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                zidonghuanhang.setBorderRight(HSSFCellStyle.BORDER_THIN);
                zidonghuanhang.setBorderTop(HSSFCellStyle.BORDER_THIN);

                // 为数据内容设置特点新单元格样式2 自动换行 上下居中左右也居中
                HSSFCellStyle zidonghuanhang2 = wb.createCellStyle();
                zidonghuanhang2.setWrapText(true);// 设置自动换行
                zidonghuanhang2
                        .setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个上下居中格式
                zidonghuanhang2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中

                // 设置边框
                zidonghuanhang2.setBottomBorderColor(HSSFColor.BLACK.index);
                zidonghuanhang2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                zidonghuanhang2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                zidonghuanhang2.setBorderRight(HSSFCellStyle.BORDER_THIN);
                zidonghuanhang2.setBorderTop(HSSFCellStyle.BORDER_THIN);
                HSSFCell datacell = null;
                // 遍历集合数据，产生数据行
                Iterator<MessageTemplateVo> it = dataList.iterator();
                int index = 0;
                while (it.hasNext()) {
                    index++;
                    row = sheet.createRow(index);
                    MessageTemplateVo t =  it.next();
                    // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
                    Field[] fields = t.getClass().getDeclaredFields();
                    for (short ii = 0; ii < fields.length; ii++) {
                        HSSFCell cell = row.createCell(ii);
                        cell.setCellStyle(style2);
                        Field field = fields[ii];
                        String fieldName = field.getName();
                        String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        try {
                            Class tCls = t.getClass();
                            Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                            Object value = getMethod.invoke(t, new Object[]{});
                            // 判断值的类型后进行强制类型转换
                            String textValue = null;
                            if (value instanceof Boolean) {
                                boolean bValue = (Boolean) value;
                                textValue = "男";
                                if (!bValue) {
                                    textValue = "女";
                                }
                            } else if (value instanceof Date) {
                                Date date = (Date) value;
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                textValue = sdf.format(date);
                            } else {
                                // 其它数据类型都当作字符串简单处理
                                textValue = value == null ? "" : value.toString();
                            }
                            // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                            if (textValue != null) {
                                Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                                Matcher matcher = p.matcher(textValue);
                                if (matcher.matches()) {
                                    cell.setCellValue(Integer.parseInt(textValue));
                                } else {
                                    HSSFRichTextString richString = new HSSFRichTextString(textValue);
                                    HSSFFont font3 = wb.createFont();
                                    font3.setColor(HSSFColor.BLUE.index);
                                    richString.applyFont(font3);
                                    cell.setCellValue(richString);
                                }
                            }
                        } catch (SecurityException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } finally {
                            // 清理资源
                        }
                    }
                }
            }

            // 第六步，将文件存到浏览器设置的下载位置
            String filename = fileName + ".xls";
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("GBK");
            response.setHeader("Content-disposition", "attachment;filename="+"Devices.xls");//Excel文件名

            // 清空response
            /*response.reset();
            // 设置response的Header
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(filename.getBytes()));*/
            //response.addHeader("Content-Length", "" + tempFile.length());
/*            OutputStream toClient = new BufferedOutputStream(
                    response.getOutputStream());*/
            //response.setContentType("application/vnd.ms-excel;charset=utf-8");
            //response.setHeader("Content-Disposition", "attachment;filename=" + filename);
           /* toClient.write(buffer);
            toClient.flush();
            toClient.close();*/
            OutputStream out = response.getOutputStream();
            try {
                wb.write(out);// 将数据写出去
                String str = "导出" + fileName + "成功！";
                System.out.println(str);
            } catch (Exception e) {
                e.printStackTrace();
                String str1 = "导出" + fileName + "失败！";
                System.out.println(str1);
            } finally {
                out.close();
            }

        } else {
            System.out.println("列数目长度名称三个数组长度要一致");
        }

    }

    public void ExportNoResponse(String sheetName, String titleName,
                                 String fileName, int columnNumber, int[] columnWidth,
                                 String[] columnName, List<ExportMessageTemplateVo> dataList) throws Exception {
        if (columnNumber == columnWidth.length&& columnWidth.length == columnName.length) {
            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet(sheetName);
            // sheet.setDefaultColumnWidth(15); //统一设置列宽
            for (int i = 0; i < columnNumber; i++) {
                for (int j = 0; j <= i; j++) {
                    if (i == j) {
                        sheet.setColumnWidth(i, columnWidth[j] * 256); // 单独设置每列的宽
                    }
                }
            }
            // 创建第0行 也就是标题
            HSSFRow row1 = sheet.createRow((int) 0);
            row1.setHeightInPoints(50);// 设备标题的高度
            // 第三步创建标题的单元格样式style2以及字体样式headerFont1
            HSSFCellStyle style2 = wb.createCellStyle();
            style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            //style2.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
            //style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            HSSFFont headerFont1 = (HSSFFont) wb.createFont(); // 创建字体样式
            headerFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
            headerFont1.setFontName("微软雅黑"); // 设置字体类型
            headerFont1.setFontHeightInPoints((short) 15); // 设置字体大小
            style2.setFont(headerFont1); // 为标题样式设置字体样式

            HSSFCell cell1 = row1.createCell(0);// 创建标题第一列
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnNumber - 1)); // 合并第0到第17列
            cell1.setCellValue(titleName); // 设置值标题
            cell1.setCellStyle(style2); // 设置标题样式

            // 创建第1行 也就是表头
            HSSFRow row = sheet.createRow((int) 1);
            row.setHeightInPoints(37);// 设置表头高度

            // 第四步，创建表头单元格样式 以及表头的字体样式
            HSSFCellStyle style = wb.createCellStyle();
            style.setWrapText(true);// 设置自动换行
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个居中格式

            style.setBottomBorderColor(HSSFColor.BLACK.index);
            /*style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);*/

            HSSFFont headerFont = (HSSFFont) wb.createFont(); // 创建字体样式
            //headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
            headerFont.setFontName("微软雅黑"); // 设置字体类型
            headerFont.setFontHeightInPoints((short) 10); // 设置字体大小
            style.setFont(headerFont); // 为标题样式设置字体样式

            // 第四.一步，创建表头的列
            HSSFRow row11 = sheet.createRow(1);
            for (int i = 0; i < columnNumber; i++) {
                //row11 = sheet.createRow(i);
                HSSFCell cell = row11.createCell(i);
                cell.setCellValue(columnName[i]);
                cell.setCellStyle(style);
            }

            // 第五步，创建单元格，并设置值
            for (int ii = 0; ii < dataList.size(); ii++) {
                row = sheet.createRow((int) ii + 2);
                // 为数据内容设置特点新单元格样式1 自动换行 上下居中
                HSSFCellStyle zidonghuanhang = wb.createCellStyle();
                zidonghuanhang.setWrapText(true);// 设置自动换行
                zidonghuanhang.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个居中格式

                // 设置边框
                zidonghuanhang.setBottomBorderColor(HSSFColor.BLACK.index);
                zidonghuanhang.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                zidonghuanhang.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                zidonghuanhang.setBorderRight(HSSFCellStyle.BORDER_THIN);
                zidonghuanhang.setBorderTop(HSSFCellStyle.BORDER_THIN);

                // 为数据内容设置特点新单元格样式2 自动换行 上下居中左右也居中
                HSSFCellStyle zidonghuanhang2 = wb.createCellStyle();
                zidonghuanhang2.setWrapText(true);// 设置自动换行
                zidonghuanhang2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个上下居中格式
                zidonghuanhang2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中

                // 设置边框
                zidonghuanhang2.setBottomBorderColor(HSSFColor.BLACK.index);
                zidonghuanhang2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                zidonghuanhang2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                zidonghuanhang2.setBorderRight(HSSFCellStyle.BORDER_THIN);
                zidonghuanhang2.setBorderTop(HSSFCellStyle.BORDER_THIN);
                HSSFCell datacell = null;
                // 遍历集合数据，产生数据行
                Iterator<ExportMessageTemplateVo> it = dataList.iterator();
                int index = 0;
                while (it.hasNext()) {
                    index++;
                    row = sheet.createRow(index+1);
                    ExportMessageTemplateVo t =  it.next();
                    // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
                    Field[] fields = t.getClass().getDeclaredFields();
                    for (short i = 0; i < fields.length; i++) {
                        HSSFCell cell = row.createCell(i);
                        cell.setCellStyle(style2);
                        Field field = fields[i];
                        String fieldName = field.getName();
                        String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        try {
                            Class tCls = t.getClass();
                            Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                            Object value = getMethod.invoke(t, new Object[]{});
                            // 判断值的类型后进行强制类型转换
                            String textValue = null;
                            if (value instanceof Date) {
                                Date date = (Date) value;
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                textValue = sdf.format(date);
                            } else {
                                // 其它数据类型都当作字符串简单处理
                                textValue = value == null ? "" : value.toString();
                            }
                            // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                            if (textValue != null) {
                                Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                                Matcher matcher = p.matcher(textValue);
                               /* if (matcher.matches()) {
                                    cell.setCellValue(Integer.parseInt(textValue));
                                } else {*/
                                    HSSFRichTextString richString = new HSSFRichTextString(textValue);
                                    HSSFFont font3 = wb.createFont();
                                    //font3.setColor(HSSFColor.BLUE.index);
                                    richString.applyFont(font3);
                                    cell.setCellValue(richString);
                                /*}*/
                            }
                        } catch (SecurityException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } finally {
                            // 清理资源
                        }
                    }
                }
            }

            // 第六步，将文件存到指定位置
            try {
                FileOutputStream fout = new FileOutputStream(FileSystemView.getFileSystemView().getHomeDirectory().getPath()+"/"+fileName+".xls");//位置
                wb.write(fout);
                String str = "导出" + fileName + "成功！";
                System.out.println(str);
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
                String str1 = "导出" + fileName + "失败！";
                System.out.println(str1);
            }
        } else {
            System.out.println("列数目长度名称三个数组长度要一致");
        }

    }
}
