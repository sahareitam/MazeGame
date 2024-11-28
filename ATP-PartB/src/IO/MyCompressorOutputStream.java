package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }


    @Override
    public void write(byte [] b) throws IOException {
        for (int i=0;i<24;i++)
            out.write(b[i]);
        int num=0;
        for (int i=24;i<b.length;i++)
        {
            if (b.length-i<8)
            {
                int len=b.length-i;
                for (int j=0;j<len;j++)
                {
                    num+=b[i]*Math.pow(2,len-1-j);
                    i++;
                }
            }
            else {
                for (int j = 0; j < 8; j++) {
                    num += b[i] * Math.pow(2, 7 - j);
                    i++;
                }
            }
            out.write(num);
            num=0;
            if (i==b.length)
                break;
            i--;
        }
    }
}