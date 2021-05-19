package com.behere.common.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.behere.common.config.FTPConfig;

import java.io.*;

public class FTPUtil {

	/**
	 * ftp上传图片方法 title:pictureUpload
	 * 
	 * @param ftpConfig
	 *            由spring管理的FtpConfig配置，在调用本方法时，可以在使用此方法的类中通过@AutoWared注入该属性。由于本方法是静态方法，所以不能在此注入该属性
	 * @param picNewName
	 *            图片新名称--防止重名 例如："1.jpg"
	 * @param picSavePath
	 *            图片保存路径。注：最后访问路径是
	 *            ftpConfig.getFTP_ADDRESS()+"/images"+picSavePath
	 * @param file
	 *            要上传的文件（图片）
	 * @return 若上传成功，返回图片的访问路径，若上传失败，返回null
	 * @throws IOException
	 */
	public static String pictureUploadByConfig(FTPConfig ftpConfig, String picNewName, InputStream inputStream)
			throws IOException {
		String picHttpPath = null;
		boolean flag = uploadFile(ftpConfig.getFTP_ADDRESS(), ftpConfig.getFTP_PORT(), ftpConfig.getFTP_USERNAME(),
				ftpConfig.getFTP_PASSWORD(), ftpConfig.getFTP_BASEPATH(), picNewName, inputStream);
		if (!flag) {
			return picHttpPath;
		}
		picHttpPath = ftpConfig.getIMAGE_BASE_URL() + picNewName;
		return picHttpPath;
	}

	/**
	 * Description: 向FTP服务器上传文件
	 * 
	 * @param host
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param basePath
	 *            FTP服务器基础目录
	 * @param filePath
	 *            FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
	 */
	public static boolean uploadFile(String host, String ftpPort, String username, String password, String basePath,
			String filename, InputStream input) {
		int port = Integer.parseInt(ftpPort);
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);
			ftp.login(username, password);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			ftp.changeWorkingDirectory(basePath);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			if (!ftp.storeFile(filename, input)) {
				return result;
			}
			input.close();
			ftp.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}
}