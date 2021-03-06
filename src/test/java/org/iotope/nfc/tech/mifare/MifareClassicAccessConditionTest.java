package org.iotope.nfc.tech.mifare;

import java.io.IOException;
import java.nio.charset.Charset;

import junit.framework.Assert;

import org.iotope.util.IOUtil;
import org.junit.Test;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.common.io.Resources;

public class MifareClassicAccessConditionTest {
    
    private MifareClassicAccessCondition getAccessConditionAndCheck(String s) throws IOException {
        String dataAsText = Resources.toString(getClass().getResource("MCACT_"+s+"_input.txt"), Charset.forName("utf8"));
        String cmpAsText = Resources.toString(getClass().getResource("MCACT_"+s+"_cmp.txt"), Charset.forName("utf8"));
        byte[] inputData = IOUtil.bin2hex(dataAsText);
        
        // Read the Access Conditions
        ByteArrayDataInput dataStreamInput = ByteStreams.newDataInput(inputData);
        MifareClassicAccessCondition mcac = new MifareClassicAccessCondition();
        mcac.read(dataStreamInput);

        // Verify against compare file
        Assert.assertEquals(cmpAsText.replaceAll("(\\r\\n)", "\n"), mcac.toString());

        
        // Write it back and verify
        ByteArrayDataOutput dataStreamOutput = ByteStreams.newDataOutput();
        mcac.write(dataStreamOutput);
        Assert.assertEquals(IOUtil.hex(inputData), IOUtil.hex(dataStreamOutput.toByteArray()));
        
        return mcac;
    }
    
    @Test
    public void factoryEmpty() throws IOException {
        getAccessConditionAndCheck("factoryEmpty");
    }

    @Test
    public void ndefPublic() throws IOException {
        getAccessConditionAndCheck("ndefPublic");
    }

    @Test
    public void mad() throws IOException {
        getAccessConditionAndCheck("mad");
    }

    
}
