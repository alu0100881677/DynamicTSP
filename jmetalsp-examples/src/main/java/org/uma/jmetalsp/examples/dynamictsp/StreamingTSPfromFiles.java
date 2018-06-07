package org.uma.jmetalsp.examples.dynamictsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.uma.jmetal.util.pseudorandom.JMetalRandom;
import org.uma.jmetalsp.StreamingDataSource;
import org.uma.jmetalsp.observeddata.SingleObservedData;
import org.uma.jmetalsp.observer.Observable;
import org.uma.jmetalsp.problem.tsp.DynamicMultiobjectiveTSP;
import org.uma.jmetalsp.problem.tsp.TSPMatrixData;

/**
 * This class emits a value periodically after a given delay (in milliseconds) for {@link DynamicMultiobjectiveTSP}
 * problem. The intervals of the random numbers should be adjusted per particular problem
 *
 * @author Antonio J. Nebro <antonio@lcc.uma.es>
 */
public class StreamingTSPfromFiles implements StreamingDataSource<SingleObservedData<TSPMatrixData>> {
  private Observable<SingleObservedData<TSPMatrixData>> observable;
  private int dataDelay ;
  private String fileName = "";

	public StreamingTSPfromFiles(Observable<SingleObservedData<TSPMatrixData>> observable, int dataDelay, String fileName) {
		this.observable = observable ;
		this.dataDelay = dataDelay ;
		this.fileName = fileName;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(dataDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			FileReader fich;
			try {
				fich = new FileReader(fileName);
				
				BufferedReader buf = new BufferedReader(fich);
				String cadena = "";
				
							
				while((cadena = buf.readLine()) != null){
					StringTokenizer tokens = new StringTokenizer(cadena, " ");
					int x = Integer.parseInt(tokens.nextToken());
					int y = Integer.parseInt(tokens.nextToken());
					Double val = Double.parseDouble(tokens.nextToken());
					System.out.println("cambio en costos: " + x + " --- " + " --- " + y + " --- " + val);
					observable.setChanged();
					observable.notifyObservers(new SingleObservedData<TSPMatrixData>(new TSPMatrixData("COST", x, y, val)));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

  @Override
  public Observable<SingleObservedData<TSPMatrixData>> getObservable() {
    return this.observable;
  }
}