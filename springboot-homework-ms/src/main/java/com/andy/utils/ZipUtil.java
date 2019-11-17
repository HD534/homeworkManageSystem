package com.andy.utils;

import org.apache.commons.io.FileUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.ArrayList;

public class ZipUtil {

	// 获取文件输入流
	private static InputStream getFileInputStreamByUrl(String filePath) throws Exception {

		String path = MyPropertiesUtil.getUploadPath();

		File file = new File(path + File.separator + filePath);

		byte[] buff = FileUtils.readFileToByteArray(file);

		return new FileInputStream(file);

	}

	// 从inStream中读取数据到byte数组中
	public static byte[] readInputStream(InputStream inStream) {
		System.out.println("------从输入流获取字节数组------");
		ByteArrayOutputStream outStream = null;
		try {
			outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			inStream.close();
			System.out.println("字节数组的长度" + buffer.length);
			System.out.println("-------返回字节数组--------");
			return outStream.toByteArray();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (outStream != null)
				try {
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

	// 压缩文件条目
	public static void getZipByUrlFile(ArrayList<String> fileUrlList, ArrayList<String> fileNameList, File desFile)
			throws Exception {
		// zip文件的文件输出流
		FileOutputStream fos = new FileOutputStream(desFile);

		// zip文件输出流
		ZipOutputStream zipOut = new ZipOutputStream(fos);
		// 压缩流设置为gbk 解决中文乱码的问题
		zipOut.setEncoding("GBK");
		for (int i = 0; i < fileUrlList.size(); i++) {

			// 获取文件Url
			String strUrl = fileUrlList.get(i);
			// 获取文件名
			String fileName = fileNameList.get(i);
			System.out.println("getZipByUrlFile 中 文件Url:" + strUrl + "  文件名:" + fileName);
			System.out.println("----------------");
			// 获取文件输入流
			InputStream is = getFileInputStreamByUrl(strUrl);
			// 通过输入流获取文件字节数组
			byte[] imgByte = readInputStream(is);
			System.out.println("----------InputStream的hashcode" + is.hashCode() + "--------");
			System.out.println("----------------");
			// 压缩文件条目
			ZipEntry ze = new ZipEntry(fileName);
			zipOut.putNextEntry(new ZipEntry(fileName));
			// 将字节数据内容写入到当前压缩条目中
			zipOut.write(imgByte, 0, imgByte.length);
			System.out.println("已压缩：" + fileName);
			// 关闭当前 ZIP 条目并定位流以写入下一个条目。
			zipOut.closeEntry();
			is.close();
		}

		System.out.println("压缩完成");
		zipOut.close();
		fos.close();

	}

	// 压缩多个文件
	public static void compressFiles(ArrayList<String> filePathList, ArrayList<String> fileNameList, File desFile) throws Exception {
		// zip文件的文件输出流
		FileOutputStream fos = new FileOutputStream(desFile);

		// zip文件输出流
		ZipOutputStream zipOut = new ZipOutputStream(fos);
		// 压缩流设置为gbk 解决中文乱码的问题
		zipOut.setEncoding("GBK");
		for (int i = 0; i < filePathList.size(); i++) {

			// 获取文件Url
			String filePath = filePathList.get(i);
			// 获取文件名
			String fileName = fileNameList.get(i);
			
			String path = MyPropertiesUtil.getUploadPath();

			File file = new File(path + File.separator + filePath);

			byte[] buff = FileUtils.readFileToByteArray(file);
			
			System.out.println("getZipByUrlFile 中 文件路径: " + filePath + "  文件名: " + fileName);
			System.out.println("----------------");
			
			// 压缩文件条目
			zipOut.putNextEntry(new ZipEntry(fileName));
			// 将字节数据内容写入到当前压缩条目中
			zipOut.write(buff, 0, buff.length);
			System.out.println("已压缩：" + fileName);
			// 关闭当前 ZIP 条目并定位流以写入下一个条目。
			zipOut.closeEntry();
		}

		System.out.println("压缩完成");
		zipOut.close();
		fos.close();
	}

	// 将多个文件打包压缩文件，返回压缩文件的路径
	public static File getZip(ArrayList<String> filePathList, ArrayList<String> fileNameList, String zipFileName)
			throws Exception {

		// 临时路径是服务器当前war下面的zip_config目录
		String tempDir = MyPropertiesUtil.getTempPath();
		// 创建压缩包文件
		File zipFile = new File(tempDir, zipFileName);

		// 判断文件是否存在 不存在新建一个
		if (!zipFile.getParentFile().exists()) {
			zipFile.getParentFile().mkdirs();
		}

		// String filePath = filepath.getAbsolutePath();
		//getZipByUrlFile(filePathList, fileNameList, zipFile);
		//压缩文件
		compressFiles(filePathList, fileNameList, zipFile);

		return zipFile;
	}

}
