package O_Callable;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class SorterCallable implements Callable<byte[]>{

	public final byte[] byteArray;
	
	public SorterCallable(byte[] byteArray) {
		this.byteArray=byteArray;
	}
	
	@Override
	public byte[] call() throws Exception {
		Arrays.sort(this.byteArray);
		return this.byteArray;
	}
}
