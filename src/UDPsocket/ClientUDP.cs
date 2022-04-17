using System;
using System.Net; // để sử dụng lớp IPAddress, IPEndPoint
using System.Net.Sockets; // để sử dụng lớp Socket
using System.Text; // để sử dụng lớp Encoding
using Newtonsoft.Json.Linq;
using System.Collections.Generic;

namespace ClientUDP
{
    class TemplateJsonObject
    {
        public List<int> position;
        public List<int> rotation;
        public List<int> scale;
    }
    internal class ClientUDP
    {
        private static IPAddress serverIp = IPAddress.Parse("127.0.0.1");
        private static int serverPort = 1108;

        private static int size = 1024;
        static void Main(string[] args)
        {
            Socket socket = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp);
            var receiveBuffer = new byte[size];
            
            String text = "{\"position\":[0,0,0],\"rotation\":[1,1,1],\"scale\":[2,2,2]}";
            byte[] sendbuf = Encoding.ASCII.GetBytes(text);
            IPEndPoint ep = new IPEndPoint(serverIp, serverPort);
            socket.SendTo(sendbuf, ep);
            Console.WriteLine("Message sent to the broadcast address: " + text);

            EndPoint dummyEndpoint = new IPEndPoint(IPAddress.Any, 0);
            int receiveBuf = socket.ReceiveFrom(receiveBuffer, ref dummyEndpoint);
            string recText = Encoding.ASCII.GetString(receiveBuffer, 0, receiveBuf);
            Console.WriteLine("Receive: " + recText);

            // string to JObject
            JObject json = JObject.Parse(recText);
            Console.WriteLine("Convert from string to Json: " + json.ToString());

            // JObject to Object (type TemplateJsonObject)
            TemplateJsonObject test = json.ToObject<TemplateJsonObject>();
            Console.WriteLine("Convert from Json to Object : " + test.rotation[1]);
            
            //Object (type TemplateJsonObject) to JObject
            JObject obj2json = JObject.FromObject(test);
            Console.WriteLine("Convert from Object to Json: " + obj2json.ToString());
        }
    }
}