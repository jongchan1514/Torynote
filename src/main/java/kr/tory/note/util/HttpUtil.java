package kr.tory.note.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class HttpUtil {
	
	//파일 업로드
	 	public static List<HashMap<String, Object>> fileUpload(MultipartHttpServletRequest request, String path) {
	 		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
	 		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
	 		// 모든 업로드된 파일
	 		List<MultipartFile> multipartFile = null;
	 		// 새로추가한것
	 		Iterator<String> iter = request.getFileNames();
	 		String str ="";
	 		
	 		while(iter.hasNext()) {
	 			str = iter.next();
	 			multipartFile = request.getFiles(str);
	 			HashMap<String, Object> fileMap = new HashMap<String, Object>();
		 		// 저장 위치 home 폴더 하위 file 하위 path
		 		String savefullPath = "";
		 		String encodeFilename = "";
		 		String orgFilename = "";
		 		String fileextension = "";
		 		String today = sf.format(new Date());
		 		String savefolder = request.getSession().getServletContext().getRealPath("/") + "resources/file/" + path + "/" + today;
		
		 		Boolean isExtension = false;
		 		String[] extension = null;
		
		 		// 게시판
		 		if (path.equalsIgnoreCase("upload")) {
		 			extension = new String[] { "jpg", "png", "gif", "mp3", "wmv", "mkv", "avi", "mp4", "csv", "xls", "xlsx",
		 					"hwp", "ppt", "pptx", "doc", "zip", "7z", "alz","txt" };
		 		}
		
		
		 		// File클래스 실제 저장
		 		File savePath = new File(savefolder);
		
		 		// 현재 폴더 경로
		 		System.out.println(savePath.getAbsolutePath());
		
		 		// 없을 경우 자동 생성
		 		if (!savePath.exists()) {
		 			savePath.mkdirs();
		 			System.out.println("폴더가 생성 되었습니다.");
		 		}
		
		 		// 파일 저장
		 		BufferedOutputStream bos = null;
		
		 		// 파일 입력
		 		if (request != null) {
		 			// 업로드된 파일 갯수만큼 반복
		 			for (MultipartFile m : multipartFile) {
		 				// 파일사이즈가 0이면 파일이 없다고 판단
		 				if (m.getSize() <= 0) {
		 					continue;
		 				}
		
		 				orgFilename = m.getOriginalFilename().substring(0, m.getOriginalFilename().lastIndexOf("."));
		 				fileextension = m.getOriginalFilename().substring(m.getOriginalFilename().lastIndexOf(".") + 1);
		 				encodeFilename = sha256(orgFilename + System.currentTimeMillis());
//		 				if (m.getOriginalFilename().contains(".")) {
//		 					fileUpload.setOrgfilename(orgFilename);
//		 					fileUpload.setFilename(encodeFilename);
//		 					fileUpload.setShortname(shortName(encodeFilename));
//		 				}
		
		 				// 해당 확장자가 일치하지 않은 파일이 있을 경우 해당 파일 업로드 중지
		 				if (path.equalsIgnoreCase("upload")) {
		 					for (String s : extension) {
		 						if (fileextension.equalsIgnoreCase(s)) {
		 							isExtension = true;
		 							break;
		 						} else {
		 							isExtension = false;
		 						}
		 					}
		 				}
		
		 				if (!isExtension) {
		 					continue;
		 				}
		
		 				savefullPath = savefolder + "/" + encodeFilename + "." + fileextension;
		 				String dbsaveFullPath = "/resources/file/" + path + "/" + today + "/" + encodeFilename + "." + fileextension;
		 				System.out.println(dbsaveFullPath);
		 				
		 				// 파일 경로 = 저장폴더 + 인코딩된 원본이름 + . + 확장자
		 				fileMap.put("Path", dbsaveFullPath);
		
		 				// 파일 용량 설정
		 				fileMap.put("Size", m.getSize());
		 				// 아이피설정1
		 				fileMap.put("Ip", request.getRemoteAddr());
		 				fileMap.put("Type", fileextension);
		
		 				// 등록일
		 				fileMap.put("Regdate", timeStamp());
		 				System.out.println(fileMap.toString());
		 				resultList.add(fileMap);
		 				
		 				try {
		 					// 암호화 하여 저장
		 					bos = new BufferedOutputStream(new FileOutputStream(savefullPath));
		 					bos.write(m.getBytes());
		 					bos.flush();
		 					bos.close();
		 				} catch (IOException e) {
		 					e.printStackTrace();
		 				}
		 			}
		 		}
	 		}
	 		return resultList;
	 	}

	 	//시간찍는거
	 	public static String timeStamp() {
	 		long time = System.currentTimeMillis();
	 		String t = String.valueOf(time / 1000);
	 		return t;
	 	}
	 	
	 // 암호화(중복방지)
	 	public static String sha256(String str) {
	 		String SHA = "";
	 		try {
	 			MessageDigest sh = MessageDigest.getInstance("SHA-256");
	 			sh.update(str.getBytes());
	 			byte byteData[] = sh.digest();
	 			StringBuffer sb = new StringBuffer();
	 			for (int i = 0; i < byteData.length; i++) {
	 				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	 			}
	 			SHA = sb.toString();
	 		} catch (NoSuchAlgorithmException e) {
	 			e.printStackTrace();
	 			SHA = null;
	 		}
	 		System.out.println(SHA.substring(30));
	 		return SHA.substring(30);
	 	}
	 	
	 	public static void sendResponceToJson(HttpServletResponse response, final HashMap<String, Object> map) { // 화면을 찾지 않고 바로 write하는 방법
	 	      response.setContentType("application/json; charset=utf-8");
	 	      JSONObject jsonObject = new JSONObject();
	 	      jsonObject = JSONObject.fromObject(JSONSerializer.toJSON(map));
	 	      try {
	 	         response.getWriter().write(jsonObject.toString()); // 따로 jsp를 안만들고 write함 java파일에서 바로 실행 가능하다는 얘기
	 	      } catch (IOException e) {
	 	         e.printStackTrace();
	 	      }
	 	   }
	 	
	 	public static void imgUpload(MultipartHttpServletRequest req, HttpServletResponse res, List<HashMap<String, Object>> files) {			
			PrintWriter printWriter = null;
			try {
				res.setHeader("charset", "utf-8");
				res.setCharacterEncoding("utf-8");
				// 화면에 찍기
				printWriter = res.getWriter();
				
				String msg = "파일 업로드가 성공적으로 완료 되었습니다.";
				// ck에서 지정된 약속
				/*
				 * <script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction('콜백의
				 * 식별 ID 값', '파일의 URL', '전송완료 메시지')</script>
				 */
				// 몇건, 이미지주소, 결과메세지 반환
				printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
						+ req.getParameter("CKEditorFuncNum") + ",'" + req.getContextPath() + files.get(0).get("Path") + "','"
						+ msg + "'" + ")</script>");
				// flush() : 파일 남아 있는거 보내라 (마저보내라)
				printWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (printWriter != null) {
						printWriter.close();
					}
				} catch (Exception e2) {
				}
			}

		}
	 	
	 	/*********비밀번호 암호화 *****************/
		public static String pwd(String passwd) {
			String sspwd="";
			try{	 
	            MessageDigest digest = MessageDigest.getInstance("SHA-256");
	            byte[] hash = digest.digest(passwd.getBytes("UTF-8"));
	            StringBuffer hexString = new StringBuffer();
	 
	            for (int i = 0; i < hash.length; i++) {
	                String hex = Integer.toHexString(0xff & hash[i]);
	                if(hex.length() == 1) hexString.append('0');
	                hexString.append(hex);
	            }
	            sspwd = hexString.toString();
	        } catch(Exception ex){
	            throw new RuntimeException(ex);
	        }
			return sspwd;
		}
}
