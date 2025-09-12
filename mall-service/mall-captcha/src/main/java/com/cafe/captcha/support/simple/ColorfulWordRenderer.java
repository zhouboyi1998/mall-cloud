package com.cafe.captcha.support.simple;

import cn.apiclub.captcha.text.renderer.WordRenderer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.captcha.support.simple
 * @Author: zhouboyi
 * @Date: 2024/3/3 2:20
 * @Description: Simple Captcha 七彩文字渲染器
 */
public class ColorfulWordRenderer implements WordRenderer {

    private static final Random RAND = new SecureRandom();

    private static final List<Color> DEFAULT_COLORS = new ArrayList<>();
    private static final List<Font> DEFAULT_FONTS = new ArrayList<>();

    private static final double X_OFFSET = 0.05D;
    private static final double Y_OFFSET = 0.25D;

    private final List<Color> colors;
    private final List<Font> fonts;

    static {
        DEFAULT_COLORS.add(Color.BLACK);

        DEFAULT_FONTS.add(new Font("Arial", Font.BOLD, 40));
        DEFAULT_FONTS.add(new Font("Courier", Font.BOLD, 40));
    }

    public ColorfulWordRenderer() {
        this(DEFAULT_COLORS, DEFAULT_FONTS);
    }

    public ColorfulWordRenderer(List<Color> colors, List<Font> fonts) {
        this.colors = new ArrayList<>();
        this.colors.addAll(colors);

        this.fonts = new ArrayList<>();
        this.fonts.addAll(fonts);
    }

    @Override
    public void render(String word, BufferedImage image) {
        Graphics2D graphics = image.createGraphics();
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        hints.add(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        graphics.setRenderingHints(hints);
        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        int xBaseline = (int) Math.round((double) image.getWidth() * X_OFFSET);
        // 文字间距
        int spacing = xBaseline;
        int yBaseline = image.getHeight() - (int) Math.round((double) image.getHeight() * Y_OFFSET);
        char[] chars = new char[1];
        char[] words = word.toCharArray();
        // 判断颜色是否足够 (颜色数量 >= 文本字符数量)
        boolean enough = colors.size() >= words.length;
        // 拷贝颜色列表到一个临时列表中
        List<Color> tempColors = new ArrayList<>(colors);
        for (char c : words) {
            chars[0] = c;
            int colorIndex = RAND.nextInt(tempColors.size());
            Color color = tempColors.get(colorIndex);
            graphics.setColor(color);
            // 如果颜色足够, 移除已使用的颜色
            if (enough) {
                tempColors.remove(colorIndex);
            }
            Font font = fonts.get(RAND.nextInt(fonts.size()));
            graphics.setFont(font);
            GlyphVector gv = font.createGlyphVector(fontRenderContext, chars);
            graphics.drawChars(chars, 0, chars.length, xBaseline, yBaseline);
            int width = (int) gv.getVisualBounds().getWidth();
            xBaseline += width + spacing;
        }
    }

    /**
     * 构造器
     */
    public static class Builder {

        private List<Color> colors;
        private List<Font> fonts;

        public Builder() {
            this.colors = DEFAULT_COLORS;
            this.fonts = DEFAULT_FONTS;
        }

        public Builder color(List<Color> colors) {
            this.colors = colors;
            return this;
        }

        public Builder font(List<Font> fonts) {
            this.fonts = fonts;
            return this;
        }

        public ColorfulWordRenderer build() {
            return new ColorfulWordRenderer(colors, fonts);
        }
    }
}
