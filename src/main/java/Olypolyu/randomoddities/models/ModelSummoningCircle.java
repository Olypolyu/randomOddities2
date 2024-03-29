package Olypolyu.randomoddities.models;

import net.minecraft.client.render.Polygon;
import net.minecraft.client.render.Vertex;
import net.minecraft.client.render.model.ModelBase;

public class ModelSummoningCircle extends ModelBase {
	private Vertex[] corners;
	private Polygon[] faces;
	private int textureU;
	private int textureV;
	private int texWidth;
	private int texHeight;

	public ModelSummoningCircle(int u, int v, int texWidth, int texHeight) {
		this.textureU = u;
		this.textureV = v;
		this.texWidth = texWidth;
        this.texHeight = texHeight;
    }

	public void addFacet(float minX, float minZ, int sizeX, int sizeZ, float expandAmount, boolean flipBottomUV) {
		this.corners = new Vertex[8];
		this.faces = new Polygon[6];

		float maxX = minX + (float)sizeX;
		float maxZ = minZ + (float)sizeZ;

		minX -= expandAmount;
		minZ -= expandAmount;
		maxX += expandAmount;
		maxZ += expandAmount;

		Vertex ptvMaxXMaxYMinZ = new Vertex(maxX, expandAmount, minZ, 8.0F, 8.0F);
		Vertex ptvMinXMaxYMinZ = new Vertex(minX, expandAmount, minZ, 8.0F, 0.0F);
		Vertex ptvMaxXMaxYMaxZ = new Vertex(maxX, expandAmount, maxZ, 8.0F, 8.0F);
		Vertex ptvMinXMaxYMaxZ = new Vertex(minX, expandAmount, maxZ, 8.0F, 0.0F);

		this.corners[2] = ptvMaxXMaxYMinZ;
		this.corners[3] = ptvMinXMaxYMinZ;
		this.corners[6] = ptvMaxXMaxYMaxZ;
		this.corners[7] = ptvMinXMaxYMaxZ;
		if (flipBottomUV) {
			this.faces[3] = new Polygon(new Vertex[]{ptvMaxXMaxYMaxZ, ptvMinXMaxYMaxZ, ptvMinXMaxYMinZ, ptvMaxXMaxYMinZ}, this.textureU + sizeZ + sizeX, this.textureV, this.textureU + sizeZ + sizeX + sizeX, this.textureV + sizeZ, this.texWidth, this.texHeight);
			this.faces[3].invertNormal = true;
		} else {
			this.faces[3] = new Polygon(new Vertex[]{ptvMaxXMaxYMinZ, ptvMinXMaxYMinZ, ptvMinXMaxYMaxZ, ptvMaxXMaxYMaxZ}, this.textureU + sizeZ + sizeX, this.textureV, this.textureU + sizeZ + sizeX + sizeX, this.textureV + sizeZ, this.texWidth, this.texHeight);
		}

	}
}
