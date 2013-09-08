package net.addictivesoftware.flow

import net.addictivesoftware.flow.objects.EventObject
import java.util.Date
import java.text.SimpleDateFormat

/**
 * Trait that contains the (static) webpages
 */
trait WebPages {
  private val dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss:SSS z")


  /**
   * Lists events
   */
  def listHtmlPage(events:List[EventObject]) =
    <html>
      <head>
        <title>Flow - List Events</title>
        <link href="/flow/css/main.css" rel="stylesheet" type="text/css"/>
      </head>
      <body>
        <h3>List Events:</h3>
        <table class="event-list">
          <tr>
            <th>TimeStamp</th>
            <th>id</th>
            <th>Session</th>
            <th>Event</th>
            <th>Data</th>
          </tr>
          {
          events.map(
            event => {
              <tr>
                <td>{
                  Option(new Date(event.timestamp)) match {
                    case Some(date:Date) => {
                      dateFormat.format(date)
                    }
                    case _ => {
                      "Illegal date: " + event.timestamp
                    }
                  }

                  }</td>
                <td>{event._id}</td>
                <td>{event.session}</td>
                <td>{event.event}</td>
                <td>
                  <table class="keyvalue-list">
                    {event.data.map(
                    entry =>
                      <tr>
                        <td>{entry._1}</td>
                        <td>{entry._2}</td>
                      </tr>
                  )
                    }
                  </table>
                </td>
              </tr>
            }
          )
          }
        </table>
      </body>
    </html>
}
