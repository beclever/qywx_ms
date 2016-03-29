package com.grgbanking.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletUtils {
	public static final char[] _hexDigits;
	private static SimpleDateFormat h;
	static {
		_hexDigits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9', 'a', 'b', 'c', 'd', 'e', 'f' };
		h = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);

	}

	public static boolean cacheControl(HttpServletRequest request,
			HttpServletResponse response, int age, int options) {
		if (age <= 0) {
			response.setHeader("Expires", "-1");
			response.setHeader("Cache-Control", "private");
			response.setHeader("Pragma ", "no-cache ");
			return true;
		}

		long time = System.currentTimeMillis();
		int seconds = (int) (time % 3600000L / 1000L);
		time /= 3600000L;
		int mod = seconds / age;
		String q = request.getQueryString();

		StringBuilder sb = new StringBuilder(16);
		ServletUtils.toHex(time, sb).append('t');
		ServletUtils.toHex(age, sb).append('m').append(mod);
		String etag = sb.toString();

		time = time * 3600000L + mod * 1000;
		String lastupdate = null;

		if (16 != (0x10 & options)) {
			String token = request.getHeader("If-None-Match");
			if (ServletUtils.isEmpty(token)) {
				String since = request.getHeader("If-Modified-Since");
				if (!(ServletUtils.isEmpty(since))) {
					lastupdate = ServletUtils.formatGMT(new Date(time));
					if (since.equals(lastupdate)) {
						response.setStatus(304);
						return false;
					}
				}
			} else if (etag.equals(token)) {
				response.setStatus(304);
				return false;
			}

			if (32 == (0x20 & options)) {
				return true;
			}

		}

		if ((2 == (0x2 & options)) && (q.length() == 0)) {
			return true;
		}
		if (lastupdate == null) {
			lastupdate = ServletUtils.formatGMT(new Date(time));
		}

		response.setHeader("Last-Modified", lastupdate);
		response.setHeader("ETag", etag);
		time = System.currentTimeMillis();
		response.setHeader("Expires",
				ServletUtils.formatGMT(new Date(time + age * 1000)));
		response.setHeader("Cache-Control", "max-age=" + age
				+ ((1 == (0x1 & options)) ? ", private" : ", public"));
		return true;
	}

	public static StringBuilder toHexFixed(int paramInt,
			StringBuilder paramStringBuilder) {
		if (paramStringBuilder == null) {
			paramStringBuilder = new StringBuilder(8);
		}
		if ((paramInt < 0) || (paramInt >= 268435456)) {
			paramStringBuilder.append(_hexDigits[(paramInt >> 28 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 24 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 20 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 16 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 12 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 8 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 4 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt & 0xF)]);
		} else if (paramInt >= 16777216) {
			paramStringBuilder.append('0');
			paramStringBuilder.append(_hexDigits[(paramInt >> 24 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 20 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 16 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 12 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 8 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 4 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt & 0xF)]);
		} else if (paramInt >= 1048576) {
			paramStringBuilder.append("00");
			paramStringBuilder.append(_hexDigits[(paramInt >> 20 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 16 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 12 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 8 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 4 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt & 0xF)]);
		} else if (paramInt >= 65536) {
			paramStringBuilder.append("000");
			paramStringBuilder.append(_hexDigits[(paramInt >> 16 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 12 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 8 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 4 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt & 0xF)]);
		} else if (paramInt >= 4096) {
			paramStringBuilder.append("0000");
			paramStringBuilder.append(_hexDigits[(paramInt >> 12 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 8 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 4 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt & 0xF)]);
		} else if (paramInt >= 256) {
			paramStringBuilder.append("00000");
			paramStringBuilder.append(_hexDigits[(paramInt >> 8 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 4 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt & 0xF)]);
		} else if (paramInt >= 16) {
			paramStringBuilder.append("000000");
			paramStringBuilder.append(_hexDigits[(paramInt >> 4 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt & 0xF)]);
		} else if (paramInt > 0) {
			paramStringBuilder.append("0000000");
			paramStringBuilder.append(_hexDigits[(paramInt & 0xF)]);
		} else {
			paramStringBuilder.append("00000000");
			return paramStringBuilder;
		}
		return paramStringBuilder;
	}

	public static StringBuilder toHexFixed(long paramLong,
			StringBuilder paramStringBuilder) {
		if (paramStringBuilder == null)
			paramStringBuilder = new StringBuilder(16);
		@SuppressWarnings("unused")
		int i1;
		toHexFixed(i1 = (int) (paramLong >> 32), paramStringBuilder);

		toHexFixed(i1 = (int) paramLong, paramStringBuilder);
		return paramStringBuilder;
	}

	public static StringBuilder toHexFixed(short paramShort,
			StringBuilder paramStringBuilder) {
		if (paramStringBuilder == null) {
			paramStringBuilder = new StringBuilder(4);
		}
		if ((paramShort < 0) || (paramShort >= 4096)) {
			paramStringBuilder.append(_hexDigits[(paramShort >> 12 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramShort >> 8 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramShort >> 4 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramShort & 0xF)]);
		} else if (paramShort >= 256) {
			paramStringBuilder.append('0');
			paramStringBuilder.append(_hexDigits[(paramShort >> 8 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramShort >> 4 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramShort & 0xF)]);
		} else if (paramShort >= 16) {
			paramStringBuilder.append("00");
			paramStringBuilder.append(_hexDigits[(paramShort >> 4 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramShort & 0xF)]);
		} else if (paramShort > 0) {
			paramStringBuilder.append("000");
			paramStringBuilder.append(_hexDigits[(paramShort & 0xF)]);
		} else {
			paramStringBuilder.append("0000");
			return paramStringBuilder;
		}
		return paramStringBuilder;
	}

	public static StringBuilder toHexFixed(byte paramByte,
			StringBuilder paramStringBuilder) {
		paramStringBuilder.append(_hexDigits[(paramByte >> 4 & 0xF)]);
		paramStringBuilder.append(_hexDigits[(paramByte & 0xF)]);
		return paramStringBuilder;
	}

	public static String toHex64(long paramLong) {
		if (0L == paramLong) {
			return "0000000000000000";
		}
		return toHexFixed(paramLong, new StringBuilder(16)).toString();
	}

	public static String toHex32(int paramInt) {
		if (paramInt == 0) {
			return "00000000";
		}
		return toHexFixed(paramInt, new StringBuilder(8)).toString();
	}

	public static String toHex16(short paramShort) {
		if (paramShort == 0) {
			return "0000";
		}
		return toHexFixed(paramShort, new StringBuilder(4)).toString();
	}

	public static StringBuilder toHex(int paramInt,
			StringBuilder paramStringBuilder) {
		if ((paramInt < 0) || (paramInt >= 268435456)) {
			paramStringBuilder.append(_hexDigits[(paramInt >> 28 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 24 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 20 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 16 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 12 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 8 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 4 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt & 0xF)]);
		} else if (paramInt >= 16777216) {
			paramStringBuilder.append(_hexDigits[(paramInt >> 24 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 20 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 16 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 12 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 8 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 4 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt & 0xF)]);
		} else if (paramInt >= 1048576) {
			paramStringBuilder.append(_hexDigits[(paramInt >> 20 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 16 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 12 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 8 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 4 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt & 0xF)]);
		} else if (paramInt >= 65536) {
			paramStringBuilder.append(_hexDigits[(paramInt >> 16 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 12 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 8 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 4 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt & 0xF)]);
		} else if (paramInt >= 4096) {
			paramStringBuilder.append(_hexDigits[(paramInt >> 12 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 8 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 4 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt & 0xF)]);
		} else if (paramInt >= 256) {
			paramStringBuilder.append(_hexDigits[(paramInt >> 8 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt >> 4 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt & 0xF)]);
		} else if (paramInt >= 16) {
			paramStringBuilder.append(_hexDigits[(paramInt >> 4 & 0xF)]);
			paramStringBuilder.append(_hexDigits[(paramInt & 0xF)]);
		} else if (paramInt > 0) {
			paramStringBuilder.append(_hexDigits[(paramInt & 0xF)]);
		} else {
			paramStringBuilder.append("0");
			return paramStringBuilder;
		}
		return paramStringBuilder;
	}

	public static String toHex(int paramInt) {
		return toHex(paramInt, new StringBuilder(8)).toString();
	}

	public static StringBuilder toHex(long paramLong,
			StringBuilder paramStringBuilder) {
		int i1;
		if ((i1 = (int) (paramLong >> 32)) != 0) {
			toHex(i1, paramStringBuilder);

			toHexFixed(i1 = (int) paramLong, paramStringBuilder);
			return paramStringBuilder;
		}

		toHex(i1 = (int) paramLong, paramStringBuilder);
		return paramStringBuilder;
	}

	public static String toHex(long paramLong) {
		return toHex(paramLong, new StringBuilder(16)).toString();
	}

	public static final String formatGMT(Date paramDate) {
		synchronized (h) {
			return h.format(paramDate);
		}
	}

	public static final boolean isEmpty(String paramString) {
		return ((paramString == null) || (paramString.length() == 0));
	}
}
