package com.redisession.catalina.session;

import org.apache.catalina.util.CustomObjectInputStream;

import com.redisession.catalina.session.RedisSession;
import com.redisession.catalina.session.Serializer;

import javax.servlet.http.HttpSession;
import java.io.*;


public class JavaSerializer implements Serializer {
  private ClassLoader loader;

  @Override
  public void setClassLoader(ClassLoader loader) {
    this.loader = loader;
  }

  @Override
  public byte[] serializeFrom(HttpSession session) throws IOException {

    RedisSession redisSession = (RedisSession) session;
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(bos));
    oos.writeLong(redisSession.getCreationTime());
    redisSession.writeObjectData(oos);

    oos.close();

    return bos.toByteArray();
  }

  @Override
  public HttpSession deserializeInto(byte[] data, HttpSession session) throws IOException, ClassNotFoundException {

    RedisSession redisSession = (RedisSession) session;

    BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(data));

    ObjectInputStream ois = new CustomObjectInputStream(bis, loader);
    redisSession.setCreationTime(ois.readLong());
    redisSession.readObjectData(ois);

    return session;
  }
}
