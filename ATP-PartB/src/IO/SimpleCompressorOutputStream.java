package IO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }


    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        int count=1;
        for (int i=0;i<24;i++)
            out.write(b[i]);
        if (b[24]==1)
            out.write(0);
        byte previous=b[24];
        for (int i=25;i<b.length;i++)
        {
            if (previous==b[i])
                count++;
            else
            {
                if (count<127)
                    out.write(count);
                else
                {
                    while (count>127)
                    {
                        out.write(127);
                        out.write(0);
                        count-=127;
                    }
                    out.write(count);
                }
                count=1;
                previous=b[i];
            }
        }
        if (count >127)
        {
            while (count>127)
            {
                out.write(127);
                out.write(0);
                count-=127;
            }
            out.write(count);
        }
        else
            out.write(count);
    }
}
