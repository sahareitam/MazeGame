package IO;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    public int read(byte[] b) throws IOException{
        for (int i=0;i<24;i++)
            b[i]=(byte) in.read();
        int temp=in.read();
        int j=24;
        byte[]reverse=new byte[8];
        while (temp!=-1)
        {
            if (b.length-j<8)
            {
                byte[] re=new byte[b.length-j];
                for (int i=0;i<b.length-j;i++)
                {
                    re[i]=(byte) (temp % 2);
                    temp /= 2;
                }
                for (int i=0;i<re.length;i++,j++)
                    b[j]=re[re.length-1-i];
            }
            else {
                for (int i = 0; i < 8; i++) {
                    reverse[i] = (byte) (temp % 2);
                    temp /= 2;
                }
                for (int i = 0; i < 8; i++, j++) {
                    b[j] = reverse[7 - i];
                }
            }
            temp=in.read();
        }
        return 0;
    }
}