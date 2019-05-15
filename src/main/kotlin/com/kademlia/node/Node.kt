package com.kademlia.node

import com.google.gson.Gson
import com.kademlia.Message
import com.kademlia.Type
import com.kademlia.routing.RoutingTable
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.ServerSocket
import java.net.Socket

data class Node(
        val id: NodeId = NodeId(),
        val routingTable: RoutingTable = RoutingTable(id)
) {
    private val gson = Gson()
    fun start() {
        ServerSocket(id.port).use { serverSocket ->
            while (true) {
                val socket = serverSocket.accept()
                val input = DataInputStream(socket.getInputStream())
                val data = input.readUTF()
                val message = gson.fromJson<Message>(data, Message::class.java)
                println("received ---> $message")

                val output = DataOutputStream(socket.getOutputStream())
                when (message.type) {
                    Type.PING -> {
                        val pong = Message(Type.PONG, id, message.from)
                        output.writeUTF(gson.toJson(pong))
                    }
                    Type.FIND_NODE -> TODO()
                    Type.FIND_VALUE -> TODO()
                    Type.STORE -> TODO()
                    else -> {
                    }
                }
            }
        }
    }

    fun ping(node: Node) {
        println("ping ---> ${node.id}")
        Socket(node.id.ip, node.id.port).use { socket ->
            // ping
            val out = DataOutputStream(socket.getOutputStream())
            val msg = Message(Type.PING, id, node.id)
            val json = gson.toJson(msg)
            out.writeUTF(json)

            val input = DataInputStream(socket.getInputStream())
            val pongString = input.readUTF()
            val message = gson.fromJson<Message>(pongString, Message::class.java)
            println("pong <--- $message")
            socket.close()
        }
    }

}