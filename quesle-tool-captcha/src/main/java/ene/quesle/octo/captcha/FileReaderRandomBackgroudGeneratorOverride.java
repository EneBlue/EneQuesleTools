package ene.quesle.octo.captcha;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;

public class FileReaderRandomBackgroudGeneratorOverride implements BackgroundGenerator{
	
	private Integer width;
	private Integer height;
	private String path;
	
	public FileReaderRandomBackgroudGeneratorOverride(Integer width, Integer height, String path){
		this.width = width;
		this.height = height;
		this.path = Thread.currentThread().getContextClassLoader()
				.getResource(path).getPath();
	}
	
	@Override
	public BufferedImage getBackground() {
		File file = new File(path);
		try {
			return this.getImage(file);
		} catch (IOException e) {
			return this.getDefaultImage();
		}
	}
	
	/**
	 * 自定义生成验证码北京图片
	 * @return
	 */
	private BufferedImage getDefaultImage(){
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				image.setRGB(i, j, 0x5B5B5B);
			}
		}
		return image;
	}
	
	/***
	 * 使用image目录下的配置图片
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private BufferedImage getImage(File file) throws IOException{
		if(file.isDirectory()){
			String[] filelist = file.list();
			
			int i = (int) (Math.random() * filelist.length);
			file = new File(this.path + filelist[i]);
		}
		return ImageIO.read(file); 
	}

	@Override
	public int getImageHeight() {
		return height;
	}

	@Override
	public int getImageWidth() {
		return width;
	}

}
