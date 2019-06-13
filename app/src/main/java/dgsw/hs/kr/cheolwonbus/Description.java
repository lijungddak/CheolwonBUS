package dgsw.hs.kr.cheolwonbus;

import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Description extends AsyncTask<Void, Void, ArrayList<BusBean>> {

    private String tmpStartStation = null;
    private String tmpEndStation = null;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<BusBean> doInBackground(Void... params) {
        ArrayList<BusBean> busBeans = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("http://www.cwg.go.kr/site/www/sub.do?key=2456").get();
            Elements elements = doc.select("table[class=table_t1]").select("tbody").select("tr");
            int size = elements.size();

            for(Element element : elements){
                BusBean busBean = null;
                if(element.childNodeSize() == 17){
                    String routeId = element.select("td").first().text();

                    String startStation = element.select("td").next().first().text();
                    this.tmpStartStation = startStation;

                    String firstViaStation = element.select("td").next().next().first().text();
                    String returnStation = element.select("td").next().next().next().first().text();
                    String secondViaStation = element.select("td").next().next().next().next().first().text();

                    String endStation = element.select("td").next().next().next().next().next().first().text();
                    this.tmpEndStation = endStation;

                    String routes = element.select("td").next().next().next().next().next().next().first().text();
                    String type = element.select("td").next().next().next().next().next().next().next().first().text();
                    busBean = new BusBean(routeId, startStation, firstViaStation, returnStation, secondViaStation,
                            endStation, routes, type);
                } else {
                    String routeId = element.select("td").first().text();
                    String startStation = this.tmpStartStation;
                    String firstViaStation = element.select("td").next().first().text();
                    String returnStation = element.select("td").next().next().first().text();
                    String secondViaStation = element.select("td").next().next().next().first().text();
                    String endStation = this.tmpEndStation;
                    String routes = element.select("td").next().next().next().next().first().text();
                    String type = element.select("td").next().next().next().next().next().first().text();

                    busBean = new BusBean(routeId, startStation, firstViaStation, returnStation, secondViaStation,
                            endStation, routes, type);
                }


                busBeans.add(busBean);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            Document doc = Jsoup.connect("http://www.cwg.go.kr/site/www/sub.do?key=1527").get();
            Elements elements = doc.select("table[class=table_t1]").select("tbody").select("tr");

            Calendar calendar = Calendar.getInstance();

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.KOREA);
            String currentTime = formatter.format(calendar.getTime());

            for (Element element: elements) {
                String routeId = element.select("td").first().text();
                String startTime;

                if(element.childNodeSize() == 21){
                    startTime = element.select("td").next().next().next().next().next().next().next().next().next().first().text();
                } else {
                    startTime = element.select("td").next().next().next().next().next().next().first().text();
                }

                String[] arrTimes = startTime.split(", ");
                ArrayList<String> times = new ArrayList<>();
                for(String time : arrTimes){
                    times.add(time);
                }

                BusBean busBean = busBeans.stream().filter(b -> b.getRouteId().equals(routeId) ).findFirst().get();

                if(busBean == null){
                    continue;
                } else {
                    busBean.setTimes(times);
                    String remainTime;
                    long min = Long.MAX_VALUE;
                    int index = 0;

                    for (int i = 0; i < times.size(); i++) {
                        Date time1 = formatter.parse(currentTime);
                        Date time2 = formatter.parse(times.get(i));
                        long diff =  time2.getTime() - time1.getTime();
                        if(Math.abs(diff) < min){
                            if(diff < 0){
                                min = Math.abs(diff);
                                index = i + 1;
                            }else {
                                min = diff;
                                index = i;
                            }
/*                            //busBean.setRemainTime(remainTime);

                            if(times.size() - 1 != i){
                                busBean.setNextTime(times.get(i+1));
                            }*/
                        }
                    }

                    if(times.size() -1 == index){ //버스가 남은게 한개
                        String time = times.get(index);
                        long diff = formatter.parse(time).getTime() - formatter.parse(currentTime).getTime();
                        long h = diff / (60000 * 60);
                        long m = diff / 60000 - (h * 60);

                        String remainingTime = String.format("%02d:%02d", h, m);

                        busBean.setRemainTime(remainingTime);
                        busBean.setNextTime("--");
                    } else {
                        busBean.setNextTime("--");
                        busBean.setRemainTime("--");
                    }

                    if(times.size() -1 > index){
                        String time = times.get(index);
                        long diff = formatter.parse(time).getTime() - formatter.parse(currentTime).getTime();
                        long h = diff / (60000 * 60);
                        long m = diff / 60000 - (h * 60);

                        String remainingTime = String.format("%02d:%02d", h, m);

                        busBean.setRemainTime(remainingTime);
                        busBean.setNextTime(times.get(index+1));
                    }

                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return busBeans;
    }
    @Override
    protected void onPostExecute(ArrayList<BusBean> result) {

    }

    public String stripHtml(String html){
        return Html.fromHtml(html).toString();
    }
}
