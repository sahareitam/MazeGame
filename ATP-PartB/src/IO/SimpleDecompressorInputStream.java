package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream  {
    private InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    public int read(byte[] b) throws IOException {
        for (int i=0;i<24;i++)
            b[i]=(byte) in.read();

        int j=24;
        int temp=in.read();
        int index=24;
        while (temp!=-1)
        {
            if (index%2==0)
            {
                for (int i=0;i<temp;i++,j++)
                    b[j]=0;
            }
            else
            {
                for (int i=0;i<temp;i++,j++)
                    b[j]=1;
            }
            index++;
            temp=in.read();
        }

        return 0;
    }
}
